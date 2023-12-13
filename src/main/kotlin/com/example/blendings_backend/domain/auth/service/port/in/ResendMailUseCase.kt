package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.MailDto

interface ResendMailUseCase {

    fun resendMail(dto: MailDto)
}