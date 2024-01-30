package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.usecase.domain.user.vo.UserModel
import com.example.blendings_backend.usecase.global.annotation.Mapper

@Mapper
object UserMapper {

    fun toModel(userJpaEntity: UserJpaEntity): UserModel =
        userJpaEntity.run { UserModel(name, nickname, birthDate, mailAddress, password, id) }

    fun toEntity(userModel: UserModel): UserJpaEntity =
        userModel.run { UserJpaEntity(name, nickname, birthDate, mailAddress, password, id) }
}