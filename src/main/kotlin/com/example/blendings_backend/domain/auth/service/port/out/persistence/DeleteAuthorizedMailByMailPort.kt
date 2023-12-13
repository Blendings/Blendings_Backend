package com.example.blendings_backend.domain.auth.service.port.out.persistence

interface DeleteAuthorizedMailByMailPort {

    fun deleteAuthorizedMailBuMail(mail: String)
}