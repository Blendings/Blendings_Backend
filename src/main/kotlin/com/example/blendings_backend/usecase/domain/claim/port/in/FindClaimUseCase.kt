package com.example.blendings_backend.usecase.domain.claim.port.`in`

import com.example.blendings_backend.usecase.domain.claim.dto.response.ClaimListResponse

interface FindClaimUseCase {
    fun findClaimList(coupleNickname: String, index: Int): ClaimListResponse
}