package com.example.blendings_backend.usecase.domain.auth.service.port.`in`

import com.example.blendings_backend.usecase.domain.auth.service.dto.LoggedUserInfoResponse
import com.example.blendings_backend.usecase.domain.auth.service.dto.LoginRequest

interface LoginUseCase {

    fun login(loginRequest: LoginRequest): LoggedUserInfoResponse
}