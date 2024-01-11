package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.vo.SentMailModel
import com.example.blendings_backend.global.annotation.Mapper

@Mapper
object SentMailMapper {

    fun toModel(sentMailEntity: SentMailEntity): SentMailModel =
        SentMailModel(
            sentMailEntity.mailAddress,
            sentMailEntity.authenticationCode
        )

    fun toEntity(sentMailModel: SentMailModel): SentMailEntity =
        SentMailEntity(
            sentMailModel.mailAddress,
            sentMailModel.authenticationCode
        )
}