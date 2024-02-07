package com.example.blendings_backend.usecase.domain.claim.port.`in`

import com.example.blendings_backend.usecase.domain.claim.dto.request.UpdateClaimRequest
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse

interface UpdateClaimUseCase {
    fun updateClaim(updateClaimRequest: UpdateClaimRequest, coupleNickname: String, id: Long): LocationKeyResponse
}