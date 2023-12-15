package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.dao.AuthorizedMailModel
import com.example.blendings_backend.global.annotation.Mapper

@Mapper
class AuthorizedMailMapper {

    fun toModel(authorizedMailEntity: AuthorizedMailEntity): AuthorizedMailModel =
        AuthorizedMailModel(
            authorizedMailEntity.mail
        )

    fun toEntity(authorizedMailModel: AuthorizedMailModel): AuthorizedMailEntity =
        AuthorizedMailEntity(
            authorizedMailModel.mail
        )
}