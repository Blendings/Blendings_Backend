package com.example.blendings_backend.domain.auth.presentation.dto

import com.example.blendings_backend.domain.auth.presentation.dto.request.AuthenticateMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.ResendMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SendMailRequest
import com.example.blendings_backend.domain.auth.service.dto.MailCodeDto
import com.example.blendings_backend.domain.auth.service.dto.MailDto
import com.example.blendings_backend.domain.auth.service.dto.SexMailDto

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
}