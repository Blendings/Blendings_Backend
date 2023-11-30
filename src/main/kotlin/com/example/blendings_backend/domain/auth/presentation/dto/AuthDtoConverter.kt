package com.example.blendings_backend.domain.auth.presentation.dto

import com.example.blendings_backend.domain.auth.presentation.dto.request.SendMailRequest
import com.example.blendings_backend.domain.auth.service.dto.SexMailDto

object AuthDtoConverter {

    fun sendMailRequestToSexMailDto(sendMailRequest: SendMailRequest): SexMailDto =
        SexMailDto(
            maleMail = sendMailRequest.maleMail,
            femaleMail = sendMailRequest.femaleMail
        )
}