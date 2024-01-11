package com.example.blendings_backend.domain.auth.service

import com.example.blendings_backend.domain.auth.service.dto.SignRequest
import com.example.blendings_backend.domain.auth.service.dto.SignInfoDto
import com.example.blendings_backend.domain.auth.service.exception.*
import com.example.blendings_backend.domain.auth.service.port.`in`.SignUseCase
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteAuthenticatedMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.ExistsAuthenticatedMailPort
import com.example.blendings_backend.domain.auth.service.vo.AuthenticatedMailAddressModel
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsUserByMailPort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveUserPort
import com.example.blendings_backend.domain.user.service.vo.CoupleMapModel
import com.example.blendings_backend.domain.user.service.vo.UserModel
import com.example.blendings_backend.global.annotation.Interactor
import com.example.blendings_backend.global.convertor.LocalDateConvertor
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate

@Interactor
class SignInteractor(
    private val passwordEncoder: PasswordEncoder,
    private val existsAuthenticatedMailPort: ExistsAuthenticatedMailPort,
    private val deleteAuthenticatedMailPort: DeleteAuthenticatedMailPort,
    private val saveUserPort: SaveUserPort,
    private val existsUserByMailPort: ExistsUserByMailPort,
    private val saveCoupleMapPort: SaveCoupleMapPort,
    private val existsCoupleMapByNicknamePort: ExistsCoupleMapByNicknamePort
) : SignUseCase {

    override fun sign(dto: SignRequest) {
        dto.run {
            verifySignDto(this)
            createAndSaveCouple(maleSignInfo, femaleSignInfo, metDay, coupleNickname)
            nullifyAuthenticationOfMailAddress(maleSignInfo.mailAddress, femaleSignInfo.mailAddress)
        }
    }

    private fun verifySignDto(dto: SignRequest) {
        dto.run {
            verifyMetDayNotAfterThanCurrentDay(metDay)
            verifyMetDayNotBeforeThanTwoBirthdays(metDay, maleSignInfo.birthDay, femaleSignInfo.birthDay)
            verifyNotSameMailAddresses(maleSignInfo.mailAddress, femaleSignInfo.mailAddress)
            verifyMailAddressesAuthenticated(maleSignInfo.mailAddress, femaleSignInfo.mailAddress)
            verifyNotDuplicatedCoupleNickname(dto.coupleNickname)
            verifyNotDuplicatedMailAddresses(maleSignInfo.mailAddress, femaleSignInfo.mailAddress)
        }
    }

    private fun createAndSaveCouple(
        maleSignInfoDto: SignInfoDto, femaleSignInfoDto: SignInfoDto, metDay: String, coupleNickname: String
    ) {
        val maleUserModel = createAndSaveUser(maleSignInfoDto)
        val femaleUserModel = createAndSaveUser(femaleSignInfoDto)
        createAndSaveCoupleMap(femaleUserModel, maleUserModel, metDay, coupleNickname)
    }

    private fun nullifyAuthenticationOfMailAddress(maleMailAddress: String, femaleMailAddress: String) {
        deleteAuthenticatedMailPort.deleteAuthenticatedMail(AuthenticatedMailAddressModel(maleMailAddress))
        deleteAuthenticatedMailPort.deleteAuthenticatedMail(AuthenticatedMailAddressModel(femaleMailAddress))
    }

    private fun verifyMetDayNotBeforeThanTwoBirthdays(
        metDay: String, firstBirthDay: String, secondBirthDay: String
    ) {
        val metDate = LocalDateConvertor.convertStringToLocalDate(metDay)
        if (metDate.isBefore(LocalDateConvertor.convertStringToLocalDate(firstBirthDay)) ||
            metDate.isBefore(LocalDateConvertor.convertStringToLocalDate(secondBirthDay))
        ) throw MetDayBeforeThanBirthdayException
    }

    private fun verifyMetDayNotAfterThanCurrentDay(metDay: String) {
        if (LocalDateConvertor.convertStringToLocalDate(metDay).isAfter(LocalDate.now()))
            throw MetDayAfterThanCurrentDayException
    }

    private fun verifyNotSameMailAddresses(maleMailAddress: String, femaleMailAddress: String) {
        if (maleMailAddress == femaleMailAddress)
            throw CoupleMailAddressesCannotSameException
    }

    private fun verifyMailAddressesAuthenticated(maleMail: String, femaleMail: String) {
        if (!existsAuthenticatedMailPort.existsAuthenticatedMailAddress(maleMail) ||
            !existsAuthenticatedMailPort.existsAuthenticatedMailAddress(femaleMail)
        ) throw UnauthenticatedMailAddressException
    }

    private fun verifyNotDuplicatedCoupleNickname(coupleNickname: String) {
        if (existsCoupleMapByNicknamePort.existsCoupleMapByNickname(coupleNickname))
            throw DuplicatedCoupleNicknameException
    }

    private fun verifyNotDuplicatedMailAddresses(maleMailAddress: String, femaleMailAddress: String) {
        if (existsUserByMailPort.existsUserByMailAddress(maleMailAddress) ||
            existsUserByMailPort.existsUserByMailAddress(femaleMailAddress)
        ) throw DuplicatedMailAddressException
    }

    private fun createAndSaveUser(signInfo: SignInfoDto): UserModel =
        saveUserPort.saveUser(signInfo.run {
            UserModel(
                name = name,
                birthDate = LocalDateConvertor.convertStringToLocalDate(birthDay),
                mailAddress = mailAddress,
                password = passwordEncoder.encode(password)
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
