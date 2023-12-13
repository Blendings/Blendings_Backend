package com.example.blendings_backend.domain.user.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface CoupleMapRepository : JpaRepository<CoupleMapJpaEntity, String>