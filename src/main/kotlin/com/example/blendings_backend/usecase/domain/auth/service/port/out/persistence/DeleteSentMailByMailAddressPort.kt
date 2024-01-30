package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

interface DeleteSentMailByMailAddressPort {

    fun deleteSentMailByMailAddress(mailAddress: String)
}