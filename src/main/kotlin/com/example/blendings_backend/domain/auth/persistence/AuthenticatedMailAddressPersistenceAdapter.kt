package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.vo.AuthenticatedMailAddressModel
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteAuthenticatedMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.ExistsAuthenticatedMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveAuthenticatedMailPort
import org.springframework.stereotype.Component

@Component
class AuthenticatedMailAddressPersistenceAdapter(
    private val authenticatedMailAddressRepository: AuthenticatedMailAddressRepository
) : SaveAuthenticatedMailPort, ExistsAuthenticatedMailByMailAddressPort, DeleteAuthenticatedMailByMailAddressPort {

    override fun saveAuthenticatedMailAddress(
        authenticatedMailAddressModel: AuthenticatedMailAddressModel
    ): AuthenticatedMailAddressModel =
        AuthenticatedMailAddressMapper.toModel(
            authenticatedMailAddressRepository.save(AuthenticatedMailAddressMapper.toEntity(authenticatedMailAddressModel))
        )

    override fun existsAuthenticatedMailAddressByMailAddress(mailAddress: String): Boolean =
        authenticatedMailAddressRepository.existsById(mailAddress)

    override fun deleteAuthenticatedMailByMailAddress(mailAddress: String) {
        authenticatedMailAddressRepository.deleteById(mailAddress)
    }
}