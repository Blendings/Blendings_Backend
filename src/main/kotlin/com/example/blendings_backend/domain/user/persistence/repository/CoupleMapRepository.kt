package com.example.blendings_backend.domain.user.persistence.repository

import com.example.blendings_backend.domain.user.persistence.entity.CoupleMapJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.Repository

interface CoupleMapRepository : Repository<CoupleMapJpaEntity, String> {

    fun save(coupleMapJpaEntity: CoupleMapJpaEntity): CoupleMapJpaEntity

    fun findByNickname(nickname: String): CoupleMapJpaEntity?
}