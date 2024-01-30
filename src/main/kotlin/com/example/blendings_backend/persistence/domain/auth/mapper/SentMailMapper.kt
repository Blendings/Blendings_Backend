package com.example.blendings_backend.persistence.domain.auth.mapper

import com.example.blendings_backend.persistence.domain.auth.entity.SentMailEntity
import com.example.blendings_backend.usecase.domain.auth.service.vo.SentMailModel
import com.example.blendings_backend.usecase.global.annotation.Mapper

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