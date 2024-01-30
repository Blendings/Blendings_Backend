package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.persistence.domain.user.mapper.CoupleMapMapper
import com.example.blendings_backend.persistence.domain.user.mapper.UserMapper
import com.example.blendings_backend.persistence.domain.user.repository.CoupleMapRepository
import com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence.FindCoupleMapByUserPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.ExistsCoupleMapByNicknamePort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.SaveCoupleMapPort
import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapModel
import com.example.blendings_backend.usecase.domain.user.vo.UserModel
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

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