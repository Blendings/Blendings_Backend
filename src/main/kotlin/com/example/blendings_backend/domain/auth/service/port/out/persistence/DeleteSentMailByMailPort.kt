package com.example.blendings_backend.domain.auth.service.port.out.persistence

interface DeleteSentMailByMailPort {

    fun deleteSentMailByMail(mail: String)
}