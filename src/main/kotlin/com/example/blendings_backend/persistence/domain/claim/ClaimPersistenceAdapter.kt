package com.example.blendings_backend.persistence.domain.claim

import com.example.blendings_backend.persistence.domain.claim.repository.ClaimRepository
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.SaveClaimPort
import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class ClaimPersistenceAdapter(
    private val claimRepository: ClaimRepository
) : SaveClaimPort {

    override fun saveClaim(claimJpaEntity: ClaimJpaEntity): ClaimJpaEntity =
        claimRepository.save(claimJpaEntity)
}