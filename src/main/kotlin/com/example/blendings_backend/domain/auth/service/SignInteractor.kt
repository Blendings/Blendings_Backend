package com.example.blendings_backend.domain.auth.service

import com.example.blendings_backend.domain.auth.service.dto.SignDto
import com.example.blendings_backend.domain.auth.service.dto.SignInfoDto
import com.example.blendings_backend.domain.auth.service.exception.*
import com.example.blendings_backend.domain.auth.service.port.`in`.SignUseCase
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteAuthorizedMailByMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.ExistsAuthorizedMailByMailPort
import com.example.blendings_backend.domain.user.service.dao.CoupleMapModel
import com.example.blendings_backend.domain.user.service.dao.UserModel
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsUserByMailPort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveUserPort
import com.example.blendings_backend.global.annotation.UseCase
import com.example.blendings_backend.global.convertor.LocalDateConvertor
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.util.*

@UseCase
class SignInteractor(
    private val passwordEncoder: PasswordEncoder,
    private val existsAuthorizedMailByMailPort: ExistsAuthorizedMailByMailPort,
    private val deleteAuthorizedMailPort: DeleteAuthorizedMailByMailPort,
    private val saveUserPort: SaveUserPort,
    private val existsUserByMailPort: ExistsUserByMailPort,
    private val saveCoupleMapPort: SaveCoupleMapPort,
    private val existsCoupleMapByNicknamePort: ExistsCoupleMapByNicknamePort
) : SignUseCase {

    override fun sign(dto: SignDto) {
        verifySignDto(dto)
        createAndSaveCouple(dto.maleSignInfo, dto.femaleSignInfo, dto.metDay, dto.coupleNickname)
        nullifyAuthenticationOfMail(dto)
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
