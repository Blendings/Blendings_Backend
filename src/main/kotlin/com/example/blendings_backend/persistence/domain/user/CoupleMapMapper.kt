package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapModel
import com.example.blendings_backend.usecase.global.annotation.Mapper

@Mapper
object CoupleMapMapper {

    fun toModel(coupleMapJpaEntity: CoupleMapJpaEntity): CoupleMapModel =
        coupleMapJpaEntity.run {
            CoupleMapModel(
                UserMapper.toModel(maleUser),
                UserMapper.toModel(femaleUser),
                metDate,
                nickname
            )
        }

    fun toEntity(coupleMapModel: CoupleMapModel): CoupleMapJpaEntity =
        coupleMapModel.run {
            CoupleMapJpaEntity(
                UserMapper.toEntity(maleUser),
                UserMapper.toEntity(femaleUser),
                metDate,
                nickname
            )
        }
}