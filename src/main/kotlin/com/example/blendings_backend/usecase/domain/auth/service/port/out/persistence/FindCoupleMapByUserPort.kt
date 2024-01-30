package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapJpaEntity
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity

interface FindCoupleMapByUserPort {
    fun findCoupleMapByUser(userJpaEntity: UserJpaEntity): CoupleMapJpaEntity?
}