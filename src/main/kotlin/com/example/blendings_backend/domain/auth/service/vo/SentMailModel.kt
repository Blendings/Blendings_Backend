package com.example.blendings_backend.domain.auth.service.vo

data class SentMailModel(
    val mailAddress: String,
    val authenticationCode: String
)