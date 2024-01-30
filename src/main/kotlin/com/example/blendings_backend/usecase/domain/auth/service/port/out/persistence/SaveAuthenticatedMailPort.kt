package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

import com.example.blendings_backend.usecase.domain.auth.service.vo.AuthenticatedMailAddressRedisEntity

interface SaveAuthenticatedMailPort {

    fun saveAuthenticatedMailAddress(entity: AuthenticatedMailAddressRedisEntity): AuthenticatedMailAddressRedisEntity
}