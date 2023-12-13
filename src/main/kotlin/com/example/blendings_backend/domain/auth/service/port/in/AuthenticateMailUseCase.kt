package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.MailCodeDto

interface AuthenticateMailUseCase {

    fun authenticateMail(dto: MailCodeDto)
}