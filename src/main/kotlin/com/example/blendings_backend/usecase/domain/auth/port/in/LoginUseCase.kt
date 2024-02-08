package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.response.LoggedUserInfoResponse
import com.example.blendings_backend.usecase.domain.auth.dto.request.LoginRequest

interface LoginUseCase {

    fun login(loginRequest: LoginRequest): LoggedUserInfoResponse
}