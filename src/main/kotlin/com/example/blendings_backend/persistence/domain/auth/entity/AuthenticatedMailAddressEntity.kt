package com.example.blendings_backend.persistence.domain.auth.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 600)
data class AuthenticatedMailAddressEntity(

    @Id
    val mailAddress: String
)
