package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.dao.SentMailModel
import org.springframework.stereotype.Component

@Component
class SentMailMapper {

    fun toModel(sentMailEntity: SentMailEntity): SentMailModel =
        SentMailModel(
            sentMailEntity.mail,
            sentMailEntity.authenticationCode
        )

    fun toEntity(sentMailModel: SentMailModel): SentMailEntity =
        SentMailEntity(
            sentMailModel.mail,
            sentMailModel.authenticationCode
        )
}