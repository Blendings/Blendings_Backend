package com.example.blendings_backend.domain.auth.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(timeToLive = 60 * 5)
data class SentMailEntity(

    @Id
    val mailAddress: String,

    @Indexed
    val authenticationCode: String
)
