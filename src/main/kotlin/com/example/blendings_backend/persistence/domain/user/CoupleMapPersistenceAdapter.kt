package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.persistence.domain.user.repository.CoupleMapRepository
import com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence.FindCoupleMapByUserPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapJpaEntity
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class CoupleMapPersistenceAdapter(
    private val coupleMapRepository: CoupleMapRepository
) : SaveCoupleMapPort,
    FindCoupleMapByUserPort,
    ExistsCoupleMapByNicknamePort {

    override fun saveCoupleMap(coupleMapJpaEntity: CoupleMapJpaEntity): CoupleMapJpaEntity =
        coupleMapRepository.save(coupleMapJpaEntity)

    override fun findCoupleMapByUser(userJpaEntity: UserJpaEntity): CoupleMapJpaEntity? =
        coupleMapRepository.findByUser(userJpaEntity)

    override fun existsCoupleMapByNickname(nickname: String): Boolean =
        coupleMapRepository.findByNickname(nickname) != null
}