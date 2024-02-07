package com.example.blendings_backend.usecase.domain.claim.dto.request

data class UpdateClaimRequest(
    val usedAt: String,
    val cost: Long,
    val date: String
)
