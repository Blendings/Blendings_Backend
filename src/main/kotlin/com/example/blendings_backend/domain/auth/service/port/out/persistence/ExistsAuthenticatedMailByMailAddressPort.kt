package com.example.blendings_backend.domain.auth.service.port.out.persistence

interface ExistsAuthenticatedMailByMailAddressPort {

    fun existsAuthenticatedMailAddressByMailAddress(mailAddress: String): Boolean
}