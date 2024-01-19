package com.example.blendings_backend.domain.user.persistence.mapper

import com.example.blendings_backend.domain.user.persistence.entity.UserJpaEntity
import com.example.blendings_backend.domain.user.service.vo.UserModel
import com.example.blendings_backend.global.annotation.Mapper

@Mapper
object UserMapper {

    fun toModel(userJpaEntity: UserJpaEntity): UserModel =
        userJpaEntity.run { UserModel(name, birthDate, mailAddress, password, id) }

    fun toEntity(userModel: UserModel): UserJpaEntity =
        userModel.run { UserJpaEntity(name, birthDate, mailAddress, password, id) }
}