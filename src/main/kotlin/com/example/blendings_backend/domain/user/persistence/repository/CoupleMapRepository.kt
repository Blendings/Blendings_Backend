package com.example.blendings_backend.domain.user.persistence.repository

import com.example.blendings_backend.domain.user.persistence.entity.CoupleMapJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CoupleMapRepository : JpaRepository<CoupleMapJpaEntity, String>