package com.example.blendings_backend.domain.auth.service.port.out

import com.example.blendings_backend.domain.auth.service.dto.TokenResponse

interface GenerateTokensPort {

    fun generateTokens(subject: Any): TokenResponse
}