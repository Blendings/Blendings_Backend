package com.example.blendings_backend.usecase.domain.auth.service.dto

data class LoginRequest(
    val mailAddress: String,
    val password: String
)
