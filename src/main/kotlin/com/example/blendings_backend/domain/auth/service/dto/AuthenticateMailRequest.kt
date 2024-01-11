package com.example.blendings_backend.domain.auth.service.dto

data class AuthenticateMailRequest(
    val mailAddress: String,
    val authenticationCode: String
)