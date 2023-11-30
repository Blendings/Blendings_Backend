package com.example.blendings_backend.domain.auth.service.dto

data class MailCodeDto(
    val mail: String,
    val authenticationCode: String
)