package com.example.blendings_backend.usecase.domain.auth.dto.request

data class AuthenticateMailRequest(
    val mailAddress: String,
    val authenticationCode: String
)