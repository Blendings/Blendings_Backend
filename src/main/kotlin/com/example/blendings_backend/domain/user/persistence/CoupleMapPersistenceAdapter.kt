package com.example.blendings_backend.domain.user.persistence

import com.example.blendings_backend.domain.user.service.vo.CoupleMapModel
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.global.annotation.PersistenceAdapter

@PersistenceAdapter
class CoupleMapPersistenceAdapter(
    private val coupleMapRepository: CoupleMapRepository
) : SaveCoupleMapPort,
    ExistsCoupleMapByNicknamePort {

    override fun saveCoupleMap(coupleMapModel: CoupleMapModel): CoupleMapModel =
        CoupleMapMapper.toModel(
            coupleMapRepository.save(CoupleMapMapper.toEntity(coupleMapModel))
        )

    override fun existsCoupleMapByNickname(nickname: String): Boolean =
        coupleMapRepository.existsById(nickname)
}