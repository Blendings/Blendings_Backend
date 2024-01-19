package com.example.blendings_backend.domain.user.persistence.mapper

import com.example.blendings_backend.domain.user.persistence.entity.CoupleMapJpaEntity
import com.example.blendings_backend.domain.user.service.vo.CoupleMapModel
import com.example.blendings_backend.global.annotation.Mapper

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