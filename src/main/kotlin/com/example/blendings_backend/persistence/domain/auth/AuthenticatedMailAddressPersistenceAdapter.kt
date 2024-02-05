package com.example.blendings_backend.persistence.domain.auth

import com.example.blendings_backend.persistence.domain.auth.repository.AuthenticatedMailAddressRepository
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.DeleteAuthenticatedMailPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.ExistsAuthenticatedMailPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.SaveAuthenticatedMailPort
import com.example.blendings_backend.usecase.domain.auth.vo.AuthenticatedMailAddressRedisEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class AuthenticatedMailAddressPersistenceAdapter(
    private val authenticatedMailAddressRepository: AuthenticatedMailAddressRepository
) : SaveAuthenticatedMailPort,
    ExistsAuthenticatedMailPort,
    DeleteAuthenticatedMailPort {

    override fun saveAuthenticatedMailAddress(
        entity: AuthenticatedMailAddressRedisEntity
    ): AuthenticatedMailAddressRedisEntity =
        authenticatedMailAddressRepository.save(entity)

    override fun existsAuthenticatedMailAddress(mailAddress: String): Boolean =
        authenticatedMailAddressRepository.findById(mailAddress) != null

    override fun deleteAuthenticatedMail(entity: AuthenticatedMailAddressRedisEntity) {
        authenticatedMailAddressRepository.delete(entity)
    }
}