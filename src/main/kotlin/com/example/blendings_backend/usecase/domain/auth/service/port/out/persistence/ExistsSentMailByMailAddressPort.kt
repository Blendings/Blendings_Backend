package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

interface ExistsSentMailByMailAddressPort {

    fun existsSentMailByMailAddress(mailAddress: String): Boolean
}