package com.example.blendings_backend.persistence.domain.auth.repository

import com.example.blendings_backend.usecase.domain.auth.vo.AuthenticatedMailAddressRedisEntity
import org.springframework.data.repository.Repository

interface AuthenticatedMailAddressRepository : Repository<AuthenticatedMailAddressRedisEntity, String> {

    fun save(authenticatedMailAddressRedisEntity: AuthenticatedMailAddressRedisEntity): AuthenticatedMailAddressRedisEntity

    fun findById(id: String): AuthenticatedMailAddressRedisEntity?

    fun delete(authenticatedMailAddressRedisEntity: AuthenticatedMailAddressRedisEntity)
}