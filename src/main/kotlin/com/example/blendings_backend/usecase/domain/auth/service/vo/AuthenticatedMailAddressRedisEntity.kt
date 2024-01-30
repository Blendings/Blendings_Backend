package com.example.blendings_backend.usecase.domain.auth.service.vo

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(timeToLive = 600)
class AuthenticatedMailAddressRedisEntity(
    mailAddress: String
) {
    @Id
    val mailAddress: String = mailAddress
}
