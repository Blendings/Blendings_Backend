package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.SexMailDto

interface SendMailUseCase {

    fun sendMail(dto: SexMailDto)
}