package com.example.blendings_backend.domain.auth.presentation.dto

import com.example.blendings_backend.domain.auth.presentation.dto.request.*
import com.example.blendings_backend.domain.auth.service.dto.*

object AuthDtoConverter {

    fun sendMailRequestToSexMailDto(sendMailRequest: SendMailRequest): BinaryMailAddressDto =
        BinaryMailAddressDto(
            maleMailAddress = sendMailRequest.maleMailAddress,
            femaleMailAddress = sendMailRequest.femaleMailAddress
        )

    fun resendMailRequestToMailDto(resendMailRequest: ResendMailRequest): ResendMailDto =
        ResendMailDto(
            resendMailRequest.mailAddress
        )

    fun authenticateMailRequestToMailCodeDto(authenticateMailRequest: AuthenticateMailRequest): AuthenticateMailAddressDto =
        AuthenticateMailAddressDto(
            authenticateMailRequest.mailAddress,
            authenticateMailRequest.authenticationCode
        )

    fun signRequestToSignDto(signRequest: SignRequest): SignDto =
        signRequest.run {
            SignDto(
                maleSignInfo = SignInfoDto(maleName, maleBirthDay, maleMailAddress, malePassword),
                femaleSignInfo = SignInfoDto(femaleName, femaleBirthDay, femaleMailAddress, femalePassword),
                metDay, coupleNickname
            )
        }
}