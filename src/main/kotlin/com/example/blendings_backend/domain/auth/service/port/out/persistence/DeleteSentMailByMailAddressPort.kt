package com.example.blendings_backend.domain.auth.service.port.out.persistence

interface DeleteSentMailByMailAddressPort {

    fun deleteSentMailByMailAddress(mailAddress: String)
}