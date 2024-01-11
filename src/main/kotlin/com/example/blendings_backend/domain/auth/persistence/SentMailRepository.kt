package com.example.blendings_backend.domain.auth.persistence

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SentMailRepository : CrudRepository<SentMailEntity, String> {

    fun findByAuthenticationCode(authenticationCode: String): SentMailEntity?
}