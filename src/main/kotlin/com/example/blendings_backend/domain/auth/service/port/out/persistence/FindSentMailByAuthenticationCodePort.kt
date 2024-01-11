package com.example.blendings_backend.domain.auth.service.port.out.persistence

import com.example.blendings_backend.domain.auth.service.vo.SentMailModel

interface FindSentMailByAuthenticationCodePort {

    fun findSentMailByAuthenticationCode(authenticationCode: String): SentMailModel?
}