package com.example.blendings_backend.domain.auth.service.dto

data class AuthenticateMailAddressDto(
    val mailAddress: String,
    val authenticationCode: String
)