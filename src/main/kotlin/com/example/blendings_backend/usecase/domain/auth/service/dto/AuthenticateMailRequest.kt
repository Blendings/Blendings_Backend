package com.example.blendings_backend.usecase.domain.auth.service.dto

data class AuthenticateMailRequest(
    val mailAddress: String,
    val authenticationCode: String
)