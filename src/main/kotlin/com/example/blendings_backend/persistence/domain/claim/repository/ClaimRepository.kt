package com.example.blendings_backend.persistence.domain.claim.repository

import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.Repository

interface ClaimRepository : Repository<ClaimJpaEntity, Long?> {

    fun save(claimJpaEntity: ClaimJpaEntity): ClaimJpaEntity

    fun findById(id: Long): ClaimJpaEntity?

    fun findAll(pageable: Pageable): List<ClaimJpaEntity>

    fun delete(claimJpaEntity: ClaimJpaEntity)
}