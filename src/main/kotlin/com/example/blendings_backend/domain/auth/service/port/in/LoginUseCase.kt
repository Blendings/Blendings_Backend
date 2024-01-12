package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.LoginRequest
import com.example.blendings_backend.domain.auth.service.dto.TokenResponse
import javax.servlet.http.HttpServletRequest

interface LoginUseCase {

    fun login(httpServletRequest: HttpServletRequest, loginRequest: LoginRequest): TokenResponse
}