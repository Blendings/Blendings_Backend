package com.example.blendings_backend.usecase.domain.auth.service.vo

data class SentMailModel(
    val mailAddress: String,
    val authenticationCode: String
)