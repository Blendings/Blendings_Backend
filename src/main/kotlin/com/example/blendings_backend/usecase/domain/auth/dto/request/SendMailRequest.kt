package com.example.blendings_backend.usecase.domain.auth.dto.request

data class SendMailRequest(
    val maleMailAddress: String,
    val femaleMailAddress: String
)