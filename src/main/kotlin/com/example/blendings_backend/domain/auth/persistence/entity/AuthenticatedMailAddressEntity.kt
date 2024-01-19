package com.example.blendings_backend.domain.auth.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 600)
data class AuthenticatedMailAddressEntity(

    @Id
    val mailAddress: String
)