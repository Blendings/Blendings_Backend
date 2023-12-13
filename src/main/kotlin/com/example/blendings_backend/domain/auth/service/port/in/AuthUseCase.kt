package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.*

interface AuthUseCase {

    fun sendMail(dto: SexMailDto)

    fun resendMail(dto: MailDto)

    fun authenticateMail(dto: MailCodeDto)

    fun sign(dto: SignDto)

    fun login(dto: LoginInfoDto)
}