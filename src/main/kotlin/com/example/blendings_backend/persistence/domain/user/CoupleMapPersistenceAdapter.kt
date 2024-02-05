package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.persistence.domain.user.repository.CoupleMapRepository
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.FindCoupleMapByUserPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.FindCoupleMapByNicknamePort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapJpaEntity
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class CoupleMapPersistenceAdapter(
    private val coupleMapRepository: CoupleMapRepository
) : SaveCoupleMapPort,
    FindCoupleMapByUserPort,
    FindCoupleMapByNicknamePort,
    ExistsCoupleMapByNicknamePort {

    override fun saveCoupleMap(coupleMapJpaEntity: CoupleMapJpaEntity): CoupleMapJpaEntity =
        coupleMapRepository.save(coupleMapJpaEntity)

    override fun findCoupleMapByUser(userJpaEntity: UserJpaEntity): CoupleMapJpaEntity? =
        coupleMapRepository.findByUser(userJpaEntity)

    override fun findCoupleMapByNickname(nickname: String): CoupleMapJpaEntity? =
        coupleMapRepository.findByNickname(nickname)

    override fun existsCoupleMapByNickname(nickname: String): Boolean =
        coupleMapRepository.findByNickname(nickname) != null
}