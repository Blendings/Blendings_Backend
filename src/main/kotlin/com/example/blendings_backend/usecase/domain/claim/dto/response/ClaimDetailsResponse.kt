package com.example.blendings_backend.usecase.domain.claim.dto.response

data class ClaimDetailsResponse(
    val id: Long,
    val usedAt: String,
    val cost: Long,
    val date: String,
    val userNickname: String
)
