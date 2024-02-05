package com.example.blendings_backend.usecase.domain.auth.port.out.persistence

import com.example.blendings_backend.usecase.domain.auth.vo.AuthenticatedMailAddressRedisEntity

interface DeleteAuthenticatedMailPort {

    fun deleteAuthenticatedMail(entity: AuthenticatedMailAddressRedisEntity)
}