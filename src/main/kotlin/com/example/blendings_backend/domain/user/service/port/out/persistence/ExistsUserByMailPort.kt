package com.example.blendings_backend.domain.user.service.port.out.persistence

interface ExistsUserByMailPort {

    fun existsUserByMail(mail: String): Boolean
}