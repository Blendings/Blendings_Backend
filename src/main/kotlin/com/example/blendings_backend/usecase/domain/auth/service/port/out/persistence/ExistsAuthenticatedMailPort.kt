package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

interface ExistsAuthenticatedMailPort {

    fun existsAuthenticatedMailAddress(mailAddress: String): Boolean
}