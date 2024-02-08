package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.request.SendMailRequest

interface SendAuthenticationMailUseCase {

    fun sendAuthenticationMailsToCouple(dto: SendMailRequest)
}