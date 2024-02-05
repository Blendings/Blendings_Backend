package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.SendMailRequest

interface SendAuthenticationMailUseCase {

    fun sendAuthenticationMailsToCouple(dto: SendMailRequest)
}