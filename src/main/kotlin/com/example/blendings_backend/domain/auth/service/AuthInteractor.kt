package com.example.blendings_backend.domain.auth.service

import com.example.blendings_backend.domain.auth.service.dao.AuthorizedMailModel
import com.example.blendings_backend.domain.auth.service.dao.SentMailModel
import com.example.blendings_backend.domain.auth.service.dto.*
import com.example.blendings_backend.domain.auth.service.exception.*
import com.example.blendings_backend.domain.auth.service.port.`in`.AuthUseCase
import com.example.blendings_backend.domain.auth.service.port.out.SendAuthenticationMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.*
import com.example.blendings_backend.domain.user.service.dao.CoupleMapModel
import com.example.blendings_backend.domain.user.service.dao.UserModel
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsUserByMailPort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveUserPort
import com.example.blendings_backend.global.convertor.LocalDateConvertor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.*
import kotlin.random.Random

@Transactional
@Service
class AuthInteractor(
    private val passwordEncoder: PasswordEncoder,
    private val sendAuthenticationMailPort: SendAuthenticationMailPort,
    private val saveSentMailPort: SaveSentMailPort,
    private val findSentMailByMailPort: FindSentMailByMailPort,
    private val deleteSentMailByMailPort: DeleteSentMailByMailPort,
    private val saveAuthorizedMailPort: SaveAuthorizedMailPort,
    private val existsAuthorizedMailByMailPort: ExistsAuthorizedMailByMailPort,
    private val deleteAuthorizedMailPort: DeleteAuthorizedMailByMailPort,
    private val saveUserPort: SaveUserPort,
    private val existsUserByMailPort: ExistsUserByMailPort,
    private val saveCoupleMapPort: SaveCoupleMapPort,
    private val existsCoupleMapByNicknamePort: ExistsCoupleMapByNicknamePort
) : AuthUseCase {

    override fun sendMail(dto: SexMailDto) {
        sendMailOne(dto.maleMail)
        sendMailOne(dto.femaleMail)
    }

    override fun resendMail(dto: MailDto) {
        deletePreviouslySentMail(dto.mail)
        sendMailOne(dto.mail)
    }

    override fun authenticateMail(dto: MailCodeDto) {
        val sentMail = findSentMailByMailPort.findSentMailByMail(dto.mail) ?: throw AuthenticationMailUnsentException
        if (sentMail.authenticationCode != dto.authenticationCode) throw MisMatchAuthenticationCodeException
        deleteSentMailByMailPort.deleteSentMailByMail(dto.mail)
        saveAuthorizedMailPort.saveAuthorizedMail(AuthorizedMailModel(dto.mail))
    }

    override fun sign(dto: SignDto) {
        verifySignDto(dto)
        createAndSaveCouple(dto.maleSignInfo, dto.femaleSignInfo, dto.metDay, dto.coupleNickname)
        nullifyAuthenticationOfMail(dto)
    }

    override fun login(dto: LoginInfoDto) {
        TODO("Not yet implemented")
    }

    private fun sendMailOne(mail: String) {
        findSentMailByMailPort.findSentMailByMail(mail)?.let { throw InAuthenticateMailException }
        val authenticationCode = createCode()
        sendAuthenticationMailPort.sendAuthenticationMail(mail, authenticationCode)
        saveSentMailPort.saveSentMail(SentMailModel(mail, authenticationCode))
    }

    private fun deletePreviouslySentMail(mail: String) {
        deleteSentMailByMailPort.deleteSentMailByMail(mail)
    }

    private fun verifySignDto(dto: SignDto) {
        verifyDays(dto.metDay, dto.maleSignInfo.birthDay, dto.femaleSignInfo.birthDay)
        verifyMails(dto.maleSignInfo.mail, dto.femaleSignInfo.mail)
        verifyNotDuplicatedCoupleNickname(dto.coupleNickname)
    }

    private fun createAndSaveCouple(
        maleSignInfoDto: SignInfoDto, femaleSignInfoDto: SignInfoDto, metDay: String, coupleNickname: String
    ) {
        val maleUserModel = createAndSaveUser(maleSignInfoDto)
        val femaleUserModel = createAndSaveUser(femaleSignInfoDto)
        createAndSaveCoupleMap(femaleUserModel, maleUserModel, metDay, coupleNickname)
    }

    private fun nullifyAuthenticationOfMail(dto: SignDto) {
        deleteAuthorizedMailPort.deleteAuthorizedMailBuMail(dto.maleSignInfo.mail)
        deleteAuthorizedMailPort.deleteAuthorizedMailBuMail(dto.femaleSignInfo.mail)
    }

    private fun verifyDays(metDay: String, maleBirthDay: String, femaleBirthDay: String) {
        verifyMetDayNotAfterThanCurrentDay(metDay)
        verifyMetDayNotBeforeThanTwoBirthdays(metDay, maleBirthDay, femaleBirthDay)
    }

    private fun verifyMails(maleMail: String, femaleMail: String) {
        verifyNotSameMails(maleMail, femaleMail)
        verifyAuthorizedMails(maleMail, femaleMail)
        verifyNotDuplicatedMails(maleMail, femaleMail)
    }

    private fun createCode(): String {
        val random = Random(Date().time)
        var code = ""
        for (i: Int in 1..6) {
            val char = random.nextInt(0, 35)
            code += if (char < 10) char
            else (char + 55).toChar()
        }
        return code
    }

    private fun verifyMetDayNotBeforeThanTwoBirthdays(metDay: String, firstBirthDay: String, secondBirthDay: String) {
        val metDate = LocalDateConvertor.convertStringToLocalDate(metDay)
        if (metDate.isBefore(LocalDateConvertor.convertStringToLocalDate(firstBirthDay))
            || metDate.isBefore(LocalDateConvertor.convertStringToLocalDate(secondBirthDay))
        ) throw MetDayBeforeThanBirthdayException
    }

    private fun verifyMetDayNotAfterThanCurrentDay(metDay: String) {
        if (LocalDateConvertor.convertStringToLocalDate(metDay).isAfter(LocalDate.now()))
            throw MetDayAfterThanCurrentDayException
    }

    private fun verifyNotSameMails(maleMail: String, femaleMail: String) {
        if (maleMail == femaleMail) throw CoupleMailsCannotSameException
    }

    private fun verifyAuthorizedMails(maleMail: String, femaleMail: String) {
        if (!existsAuthorizedMailByMailPort.existsAuthorizedMailByMail(maleMail)
            || !existsAuthorizedMailByMailPort.existsAuthorizedMailByMail(femaleMail)
        ) throw UnauthorizedMailException
    }

    private fun verifyNotDuplicatedMails(maleMail: String, femaleMail: String) {
        if (existsUserByMailPort.existsUserByMail(maleMail)
            || existsUserByMailPort.existsUserByMail(femaleMail)
        ) throw DuplicatedMailException
    }

    private fun verifyNotDuplicatedCoupleNickname(coupleNickname: String) {
        if (existsCoupleMapByNicknamePort.existsCoupleMapByNickname(coupleNickname))
            throw DuplicatedCoupleNicknameException
    }

    private fun createAndSaveUser(signInfo: SignInfoDto): UserModel = saveUserPort.saveUser(signInfo.run {
        UserModel(
            name,
            LocalDateConvertor.convertStringToLocalDate(birthDay),
            mail,
            passwordEncoder.encode(password),
            UUID.randomUUID()
        )
    })

    private fun createAndSaveCoupleMap(
        maleUserModel: UserModel, femaleUserModel: UserModel, metDay: String, coupleNickname: String
    ): CoupleMapModel = saveCoupleMapPort.saveCoupleMap(
        CoupleMapModel(
            maleUserModel, femaleUserModel, LocalDateConvertor.convertStringToLocalDate(metDay), coupleNickname
        )
    )
}
