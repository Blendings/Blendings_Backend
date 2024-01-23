package com.example.blendings_backend.domain.user.persistence

import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindCoupleMapByUserPort
import com.example.blendings_backend.domain.user.persistence.mapper.CoupleMapMapper
import com.example.blendings_backend.domain.user.persistence.mapper.UserMapper
import com.example.blendings_backend.domain.user.persistence.repository.CoupleMapRepository
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.domain.user.service.vo.CoupleMapModel
import com.example.blendings_backend.domain.user.service.vo.UserModel
import com.example.blendings_backend.global.annotation.PersistenceAdapter

@PersistenceAdapter
class CoupleMapPersistenceAdapter(
    private val coupleMapRepository: CoupleMapRepository
) : SaveCoupleMapPort,
    FindCoupleMapByUserPort,
    ExistsCoupleMapByNicknamePort {

    override fun saveCoupleMap(coupleMapModel: CoupleMapModel): CoupleMapModel =
        CoupleMapMapper.toModel(
            coupleMapRepository.save(CoupleMapMapper.toEntity(coupleMapModel))
        )

    override fun findCoupleMapByUser(userModel: UserModel): CoupleMapModel? =
        coupleMapRepository.findByUser(UserMapper.toEntity(userModel))?.let {
            CoupleMapMapper.toModel(it)
        }

    override fun existsCoupleMapByNickname(nickname: String): Boolean =
        coupleMapRepository.findByNickname(nickname) != null
}