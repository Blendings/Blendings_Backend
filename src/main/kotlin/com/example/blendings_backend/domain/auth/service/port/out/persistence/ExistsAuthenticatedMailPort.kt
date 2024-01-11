package com.example.blendings_backend.domain.auth.service.port.out.persistence

interface ExistsAuthenticatedMailPort {

    fun existsAuthenticatedMailAddress(mailAddress: String): Boolean
}