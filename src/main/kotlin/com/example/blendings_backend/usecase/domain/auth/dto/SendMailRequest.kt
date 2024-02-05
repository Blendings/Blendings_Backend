package com.example.blendings_backend.usecase.domain.auth.dto

data class SendMailRequest(
    val maleMailAddress: String,
    val femaleMailAddress: String
)