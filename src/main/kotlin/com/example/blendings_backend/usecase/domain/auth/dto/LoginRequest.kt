package com.example.blendings_backend.usecase.domain.auth.dto

data class LoginRequest(
    val mailAddress: String,
    val password: String
)
