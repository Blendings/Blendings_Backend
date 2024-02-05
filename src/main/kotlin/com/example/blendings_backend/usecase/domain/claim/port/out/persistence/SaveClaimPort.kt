package com.example.blendings_backend.usecase.domain.claim.port.out.persistence

import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity

interface SaveClaimPort {
    fun saveClaim(claimJpaEntity: ClaimJpaEntity): ClaimJpaEntity
}