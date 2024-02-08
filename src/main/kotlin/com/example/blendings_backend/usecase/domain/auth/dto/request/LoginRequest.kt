package com.example.blendings_backend.usecase.domain.auth.dto.request

data class LoginRequest(
    val mailAddress: String,
    val password: String
)
