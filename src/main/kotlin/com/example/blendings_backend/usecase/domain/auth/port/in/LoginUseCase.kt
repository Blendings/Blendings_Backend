package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.LoggedUserInfoResponse
import com.example.blendings_backend.usecase.domain.auth.dto.LoginRequest

interface LoginUseCase {

    fun login(loginRequest: LoginRequest): LoggedUserInfoResponse
}