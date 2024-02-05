package com.example.blendings_backend.usecase.domain.auth.port.out.persistence

import com.example.blendings_backend.usecase.domain.auth.vo.SentMailRedisEntity

interface FindSentMailByMailAddressPort {

    fun findSentMailByMailAddress(mailAddress: String): SentMailRedisEntity?
}