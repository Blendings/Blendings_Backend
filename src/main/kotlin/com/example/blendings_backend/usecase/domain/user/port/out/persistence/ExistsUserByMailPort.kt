package com.example.blendings_backend.usecase.domain.user.port.out.persistence

interface ExistsUserByMailPort {

    fun existsUserByMailAddress(mailAddress: String): Boolean
}