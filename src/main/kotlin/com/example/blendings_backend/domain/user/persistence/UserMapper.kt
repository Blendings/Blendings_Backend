package com.example.blendings_backend.domain.user.persistence

import com.example.blendings_backend.domain.user.service.dao.UserModel
import com.example.blendings_backend.global.annotation.Mapper

@Mapper
object UserMapper {

    fun toModel(userJpaEntity: UserJpaEntity): UserModel =
        userJpaEntity.run { UserModel(name, birthDate, mail, password, id) }

    fun toEntity(userModel: UserModel): UserJpaEntity =
        userModel.run { UserJpaEntity(name, birthDay, mail, password, id) }
}