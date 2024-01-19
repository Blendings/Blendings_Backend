package com.example.blendings_backend.domain.auth.persistence.mapper

import com.example.blendings_backend.domain.auth.persistence.entity.AuthenticatedMailAddressEntity
import com.example.blendings_backend.domain.auth.service.vo.AuthenticatedMailAddressModel
import com.example.blendings_backend.global.annotation.Mapper

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