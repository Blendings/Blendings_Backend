package com.example.blendings_backend.domain.auth.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 60 * 5)
data class SentMailEntity(

    @Id
    val mailAddress: String,

    val authenticationCode: String
)
