package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.ResendMailRequest

interface ResendMailUseCase {

    fun resendMail(dto: ResendMailRequest)
}