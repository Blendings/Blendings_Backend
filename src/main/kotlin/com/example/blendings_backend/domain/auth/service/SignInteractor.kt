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
        nullifyAuthenticationOfMail(dto.maleSignInfo.mail, dto.femaleSignInfo.mail)
    }

    private fun verifySignDto(dto: SignDto) {
        dto.run {
            verifyMetDayNotAfterThanCurrentDay(metDay)
            verifyMetDayNotBeforeThanTwoBirthdays(metDay, maleSignInfo.birthDay, femaleSignInfo.birthDay)
            verifyNotSameMails(maleSignInfo.mail, femaleSignInfo.mail)
            verifyAuthorizedMails(maleSignInfo.mail, femaleSignInfo.mail)
            verifyNotDuplicatedCoupleNickname(dto.coupleNickname)
            verifyNotDuplicatedMails(maleSignInfo.mail, femaleSignInfo.mail)
        }
    }

    private fun createAndSaveCouple(
        maleSignInfoDto: SignInfoDto, femaleSignInfoDto: SignInfoDto, metDay: String, coupleNickname: String
    ) {
        val maleUserModel = createAndSaveUser(maleSignInfoDto)
        val femaleUserModel = createAndSaveUser(femaleSignInfoDto)
        createAndSaveCoupleMap(femaleUserModel, maleUserModel, metDay, coupleNickname)
    }

    private fun nullifyAuthenticationOfMail(maleMail: String, femaleMail: String) {
        deleteAuthorizedMailPort.deleteAuthorizedMailBuMail(maleMail)
        deleteAuthorizedMailPort.deleteAuthorizedMailBuMail(femaleMail)
    }

    private fun verifyMetDayNotBeforeThanTwoBirthdays(metDay: String, firstBirthDay: String, secondBirthDay: String) {
        val metDate = LocalDateConvertor.convertStringToLocalDate(metDay)
        if (metDate.isBefore(LocalDateConvertor.convertStringToLocalDate(firstBirthDay)) ||
            metDate.isBefore(LocalDateConvertor.convertStringToLocalDate(secondBirthDay))
        ) throw MetDayBeforeThanBirthdayException
    }

    private fun verifyMetDayNotAfterThanCurrentDay(metDay: String) {
        if (LocalDateConvertor.convertStringToLocalDate(metDay).isAfter(LocalDate.now()))
            throw MetDayAfterThanCurrentDayException
    }

    private fun verifyNotSameMails(maleMail: String, femaleMail: String) {
        if (maleMail == femaleMail)
            throw CoupleMailsCannotSameException
    }

    private fun verifyAuthorizedMails(maleMail: String, femaleMail: String) {
        if (!existsAuthorizedMailByMailPort.existsAuthorizedMailByMail(maleMail) ||
            !existsAuthorizedMailByMailPort.existsAuthorizedMailByMail(femaleMail)
        ) throw UnauthorizedMailException
    }

    private fun verifyNotDuplicatedCoupleNickname(coupleNickname: String) {
        if (existsCoupleMapByNicknamePort.existsCoupleMapByNickname(coupleNickname))
            throw DuplicatedCoupleNicknameException
    }

    private fun verifyNotDuplicatedMails(maleMail: String, femaleMail: String) {
        if (existsUserByMailPort.existsUserByMail(maleMail) ||
            existsUserByMailPort.existsUserByMail(femaleMail)
        ) throw DuplicatedMailException
    }

    private fun createAndSaveUser(signInfo: SignInfoDto): UserModel =
        saveUserPort.saveUser(signInfo.run {
            UserModel(
                name = name,
                birthDay = LocalDateConvertor.convertStringToLocalDate(birthDay),
                mail = mail,
                password = passwordEncoder.encode(password),
                id = UUID.randomUUID()
            )
        })

    private fun createAndSaveCoupleMap(
        maleUserModel: UserModel, femaleUserModel: UserModel, metDay: String, coupleNickname: String
    ): CoupleMapModel =
        saveCoupleMapPort.saveCoupleMap(
            CoupleMapModel(
                maleUser = maleUserModel,
                femaleUser = femaleUserModel,
                metDate = LocalDateConvertor.convertStringToLocalDate(metDay),
                nickname = coupleNickname
            )
        )
}
