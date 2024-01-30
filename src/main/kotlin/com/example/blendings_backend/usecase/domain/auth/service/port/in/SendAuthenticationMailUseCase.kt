package com.example.blendings_backend.usecase.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.SendMailRequest

interface SendAuthenticationMailUseCase {

    fun sendAuthenticationMailsToCouple(dto: SendMailRequest)
}