package com.example.blendings_backend.usecase.domain.claim.port.`in`

interface DeleteClaimUseCase {
    fun deleteClaim(coupleNickname: String, id: Long)
}