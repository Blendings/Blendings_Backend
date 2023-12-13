package com.example.blendings_backend.domain.auth.service.port.out.persistence

interface ExistsAuthorizedMailByMailPort {

    fun existsAuthorizedMailByMail(mail: String): Boolean
}