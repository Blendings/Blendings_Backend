package com.example.blendings_backend.domain.auth.persistence

import org.springframework.data.repository.CrudRepository

interface SentMailRepository : CrudRepository<SentMailEntity, String> {

    fun findByAuthenticationCode(authenticationCode: String): SentMailEntity?
}