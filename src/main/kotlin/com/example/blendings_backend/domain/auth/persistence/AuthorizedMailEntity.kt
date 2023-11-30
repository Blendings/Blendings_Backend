package com.example.blendings_backend.domain.auth.persistence

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 600)
data class AuthorizedMailEntity(

    @Id
    val mail: String
)
