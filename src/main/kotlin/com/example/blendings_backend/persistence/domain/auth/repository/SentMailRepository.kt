package com.example.blendings_backend.persistence.domain.auth.repository

import com.example.blendings_backend.usecase.domain.auth.service.vo.SentMailRedisEntity
import org.springframework.data.repository.Repository

interface SentMailRepository : Repository<SentMailRedisEntity, String> {

    fun save(sentMailRedisEntity: SentMailRedisEntity): SentMailRedisEntity

    fun findById(id: String): SentMailRedisEntity?

    fun deleteById(id: String)
}