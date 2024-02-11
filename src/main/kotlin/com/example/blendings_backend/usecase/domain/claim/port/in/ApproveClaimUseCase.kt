package com.example.blendings_backend.usecase.domain.claim.port.`in`

interface ApproveClaimUseCase {
    fun approveClaim(coupleNickname: String, id: Long)
}