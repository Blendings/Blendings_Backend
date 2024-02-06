package com.example.blendings_backend.usecase.domain.claim.port.`in`

import com.example.blendings_backend.usecase.domain.claim.dto.request.CreateClaimRequest
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse

interface CreateClaimUseCase {
    fun createClaim(coupleNickname: String, createClaimRequest: CreateClaimRequest): LocationKeyResponse
}