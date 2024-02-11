package com.example.blendings_backend.usecase.domain.claim.port.out.persistence

import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import org.springframework.data.domain.PageRequest

interface FindClaimPort {
    fun findById(id: Long): ClaimJpaEntity?
    fun findByCoupleNicknamePageable(coupleNickname: String, pageRequest: PageRequest): List<ClaimJpaEntity>
}