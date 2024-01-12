package com.example.blendings_backend.domain.auth.service.dto

data class LoginRequest(
    val mailAddress: String,
    val password: String
)
