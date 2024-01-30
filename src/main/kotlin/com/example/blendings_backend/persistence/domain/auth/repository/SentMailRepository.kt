package com.example.blendings_backend.persistence.domain.auth.repository

import com.example.blendings_backend.persistence.domain.auth.entity.SentMailEntity
import org.springframework.data.repository.Repository

interface SentMailRepository : Repository<SentMailEntity, String> {

    fun save(sentMailEntity: SentMailEntity): SentMailEntity

    fun findById(id: String): SentMailEntity?

    fun deleteById(id: String)
}