package com.example.blendings_backend.domain.auth.service.dto

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
