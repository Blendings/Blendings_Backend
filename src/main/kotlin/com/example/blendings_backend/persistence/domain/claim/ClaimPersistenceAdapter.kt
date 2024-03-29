package com.example.blendings_backend.persistence.domain.claim

import com.example.blendings_backend.persistence.domain.claim.repository.ClaimRepository
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.DeleteClaimPort
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.FindClaimPort
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.SaveClaimPort
import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter
import org.springframework.data.domain.PageRequest

@PersistenceAdapter
class ClaimPersistenceAdapter(
    private val claimRepository: ClaimRepository
) : SaveClaimPort,
    FindClaimPort,
    DeleteClaimPort {

    override fun saveClaim(claimJpaEntity: ClaimJpaEntity): ClaimJpaEntity =
        claimRepository.save(claimJpaEntity)

    override fun findById(id: Long): ClaimJpaEntity? =
        claimRepository.findById(id)

    override fun findByCoupleNicknamePageable(coupleNickname: String, pageRequest: PageRequest): List<ClaimJpaEntity> =
        claimRepository.findAllByCoupleNickname(coupleNickname, pageRequest)

    override fun delete(claimJpaEntity: ClaimJpaEntity) {
        claimRepository.delete(claimJpaEntity)
    }
}