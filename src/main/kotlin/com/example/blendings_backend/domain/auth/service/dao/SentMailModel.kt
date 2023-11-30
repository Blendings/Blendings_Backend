package com.example.blendings_backend.domain.auth.service.dao

data class SentMailModel(
    val mail: String,
    val authenticationCode: String
)