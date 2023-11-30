package com.example.blendings_backend.domain.auth.service.port.out.persistence

import com.example.blendings_backend.domain.auth.service.dao.SentMailModel

interface FindSentMailByMailPort {

    fun findSentMailByMail(mail: String): SentMailModel?
}