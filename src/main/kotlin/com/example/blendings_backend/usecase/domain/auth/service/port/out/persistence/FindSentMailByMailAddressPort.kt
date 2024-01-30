package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

import com.example.blendings_backend.usecase.domain.auth.service.vo.SentMailRedisEntity

interface FindSentMailByMailAddressPort {

    fun findSentMailByMailAddress(mailAddress: String): SentMailRedisEntity?
}