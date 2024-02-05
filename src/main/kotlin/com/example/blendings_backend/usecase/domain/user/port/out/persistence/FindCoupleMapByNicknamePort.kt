package com.example.blendings_backend.usecase.domain.user.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapJpaEntity

interface FindCoupleMapByNicknamePort {
    fun findCoupleMapByNickname(nickname: String): CoupleMapJpaEntity?
}