package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.LoginInfoDto

interface LoginUseCase {

    fun login(dto: LoginInfoDto)
}