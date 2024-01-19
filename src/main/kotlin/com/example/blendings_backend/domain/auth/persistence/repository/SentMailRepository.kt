package com.example.blendings_backend.domain.auth.persistence.repository

import com.example.blendings_backend.domain.auth.persistence.entity.SentMailEntity
import org.springframework.data.repository.Repository

interface SentMailRepository : Repository<SentMailEntity, String> {

    fun save(sentMailEntity: SentMailEntity): SentMailEntity

    fun findByMailAddress(mailAddress: String): SentMailEntity?

    fun findByAuthenticationCode(authenticationCode: String): SentMailEntity?

    fun deleteByMailAddress(mailAddress: String)
}