package com.example.blendings_backend.persistence.domain.auth.mapper

import com.example.blendings_backend.persistence.domain.auth.entity.AuthenticatedMailAddressEntity
import com.example.blendings_backend.usecase.domain.auth.service.vo.AuthenticatedMailAddressModel
import com.example.blendings_backend.usecase.global.annotation.Mapper

@Mapper
object AuthenticatedMailAddressMapper {

    fun toModel(authenticatedMailAddressEntity: AuthenticatedMailAddressEntity): AuthenticatedMailAddressModel =
        AuthenticatedMailAddressModel(
            authenticatedMailAddressEntity.mailAddress
        )

    fun toEntity(authenticatedMailAddressModel: AuthenticatedMailAddressModel): AuthenticatedMailAddressEntity =
        AuthenticatedMailAddressEntity(
            authenticatedMailAddressModel.mailAddress
        )
}