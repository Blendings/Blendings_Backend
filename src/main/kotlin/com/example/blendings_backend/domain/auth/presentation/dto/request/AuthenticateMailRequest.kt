package com.example.blendings_backend.domain.auth.presentation.dto.request

data class AuthenticateMailRequest(
    val mail: String,
    val authenticationCode: String
)