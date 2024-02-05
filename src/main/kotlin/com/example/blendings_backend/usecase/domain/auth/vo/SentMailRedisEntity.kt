package com.example.blendings_backend.usecase.domain.auth.vo

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 60 * 5)
class SentMailRedisEntity(
    mailAddress: String,
    authenticationCode: String
) {

    @Id
    val mailAddress: String = mailAddress

    val authenticationCode: String = authenticationCode
}
