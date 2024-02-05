package com.example.blendings_backend.usecase.domain.auth.port.out.persistence

import com.example.blendings_backend.usecase.domain.auth.vo.SentMailRedisEntity

interface SaveSentMailPort {

    fun saveSentMail(sentMailRedisEntity: SentMailRedisEntity): SentMailRedisEntity
}