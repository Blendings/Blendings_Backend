package com.example.blendings_backend.domain.auth.service.port.out.persistence

interface DeleteAuthenticatedMailByMailAddressPort {

    fun deleteAuthenticatedMailByMailAddress(mailAddress: String)
}