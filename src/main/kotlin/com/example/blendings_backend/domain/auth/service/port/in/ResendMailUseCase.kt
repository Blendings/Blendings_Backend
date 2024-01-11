package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.ResendMailRequest

interface ResendMailUseCase {

    fun resendMail(dto: ResendMailRequest)
}