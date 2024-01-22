package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.LoginRequest
import com.example.blendings_backend.domain.auth.service.dto.LoggedUserInfoResponse

interface LoginUseCase {

    fun login(loginRequest: LoginRequest): LoggedUserInfoResponse
}