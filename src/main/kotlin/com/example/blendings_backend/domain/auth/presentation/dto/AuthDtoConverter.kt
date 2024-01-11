package com.example.blendings_backend.domain.auth.presentation.dto

import com.example.blendings_backend.domain.auth.presentation.dto.request.*
import com.example.blendings_backend.domain.auth.service.dto.*

object AuthDtoConverter {

    fun sendMailRequestToSexMailDto(sendMailWebRequest: SendMailWebRequest): SendMailRequest =
        SendMailRequest(
            maleMailAddress = sendMailWebRequest.maleMailAddress,
            femaleMailAddress = sendMailWebRequest.femaleMailAddress
        )

    fun resendMailRequestToMailDto(resendMailWebRequest: ResendMailWebRequest): ResendMailRequest =
        ResendMailRequest(
            mailAddress = resendMailWebRequest.mailAddress
        )

    fun authenticateMailRequestToMailCodeDto(authenticateMailWebRequest: AuthenticateMailWebRequest): AuthenticateMailRequest =
        AuthenticateMailRequest(
            mailAddress = authenticateMailWebRequest.mailAddress,
            authenticationCode = authenticateMailWebRequest.authenticationCode
        )

    fun signRequestToSignDto(signWebRequest: SignWebRequest): SignRequest =
        signWebRequest.run {
            SignRequest(
                maleSignInfo = SignInfoDto(maleName, maleBirthDay, maleMailAddress, malePassword),
                femaleSignInfo = SignInfoDto(femaleName, femaleBirthDay, femaleMailAddress, femalePassword),
                metDay = metDay,
                coupleNickname = coupleNickname
            )
        }
}