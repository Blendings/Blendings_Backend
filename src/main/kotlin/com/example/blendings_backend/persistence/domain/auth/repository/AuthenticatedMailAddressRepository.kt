package com.example.blendings_backend.persistence.domain.auth.repository

import com.example.blendings_backend.persistence.domain.auth.entity.AuthenticatedMailAddressEntity
import org.springframework.data.repository.Repository

interface AuthenticatedMailAddressRepository : Repository<AuthenticatedMailAddressEntity, String> {

    fun save(authenticatedMailAddressEntity: AuthenticatedMailAddressEntity): AuthenticatedMailAddressEntity

    fun findById(id: String): AuthenticatedMailAddressEntity?

    fun delete(authenticatedMailAddressEntity: AuthenticatedMailAddressEntity)
}