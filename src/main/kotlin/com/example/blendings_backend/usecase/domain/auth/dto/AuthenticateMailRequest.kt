package com.example.blendings_backend.usecase.domain.auth.dto

data class AuthenticateMailRequest(
    val mailAddress: String,
    val authenticationCode: String
)