package com.example.blendings_backend.usecase.domain.user.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapJpaEntity

interface SaveCoupleMapPort {

    fun saveCoupleMap(coupleMapJpaEntity: CoupleMapJpaEntity): CoupleMapJpaEntity
}