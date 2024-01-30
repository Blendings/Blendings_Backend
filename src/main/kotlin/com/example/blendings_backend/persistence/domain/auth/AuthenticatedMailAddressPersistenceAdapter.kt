package com.example.blendings_backend.persistence.domain.auth

import com.example.blendings_backend.persistence.domain.auth.mapper.AuthenticatedMailAddressMapper
import com.example.blendings_backend.persistence.domain.auth.repository.AuthenticatedMailAddressRepository
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteAuthenticatedMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.ExistsAuthenticatedMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveAuthenticatedMailPort
import com.example.blendings_backend.domain.auth.service.vo.AuthenticatedMailAddressModel
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class AuthenticatedMailAddressPersistenceAdapter(
    private val authenticatedMailAddressRepository: AuthenticatedMailAddressRepository
) : SaveAuthenticatedMailPort,
    ExistsAuthenticatedMailPort,
    DeleteAuthenticatedMailPort {

    override fun saveAuthenticatedMailAddress(
        domain: AuthenticatedMailAddressModel
    ): AuthenticatedMailAddressModel =
        AuthenticatedMailAddressMapper.toModel(
            authenticatedMailAddressRepository.save(
                AuthenticatedMailAddressMapper.toEntity(domain)
            )
        )

    override fun existsAuthenticatedMailAddress(mailAddress: String): Boolean =
        authenticatedMailAddressRepository.findById(mailAddress) != null

    override fun deleteAuthenticatedMail(model: AuthenticatedMailAddressModel) {
        authenticatedMailAddressRepository.delete(
            AuthenticatedMailAddressMapper.toEntity(model)
        )
    }
}