package com.example.blendings_backend.domain.auth.presentation.dto

import com.example.blendings_backend.domain.auth.presentation.dto.request.*
import com.example.blendings_backend.domain.auth.service.dto.*

object AuthDtoConverter {

    fun sendMailRequestToSexMailDto(sendMailRequest: SendMailRequest): SexMailDto =
        SexMailDto(
            maleMail = sendMailRequest.maleMail,
            femaleMail = sendMailRequest.femaleMail
        )

    fun resendMailRequestToMailDto(resendMailRequest: ResendMailRequest): MailDto =
        MailDto(
            resendMailRequest.mail
        )

    fun authenticateMailRequestToMailCodeDto(authenticateMailRequest: AuthenticateMailRequest): MailCodeDto =
        MailCodeDto(
            authenticateMailRequest.mail,
            authenticateMailRequest.authenticationCode
        )

    fun signRequestToSignDto(signRequest: SignRequest): SignDto =
        signRequest.run {
            SignDto(
                maleSignInfo = SignInfoDto(maleName, maleBirthDay, maleMail, malePassword),
                femaleSignInfo = SignInfoDto(femaleName, femaleBirthDay, femaleMail, femalePassword),
                metDay, coupleNickname
            )
        }
}