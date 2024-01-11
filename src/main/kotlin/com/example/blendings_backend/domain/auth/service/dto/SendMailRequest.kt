package com.example.blendings_backend.domain.auth.service.dto

data class SendMailRequest(
    val maleMailAddress: String,
    val femaleMailAddress: String
)