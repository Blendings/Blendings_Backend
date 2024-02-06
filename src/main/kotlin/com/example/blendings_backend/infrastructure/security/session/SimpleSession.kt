package com.example.blendings_backend.infrastructure.security.session

import com.example.blendings_backend.infrastructure.security.session.manager.SessionManager
import java.time.LocalDateTime
import java.util.*

class SimpleSession(
    private val userMailAddress: String,
    private val coupleNickname: String,
    private val sm: SessionManager
) : Session {

    private val sessionId: String = UUID.randomUUID().toString()

    private val createdAt: LocalDateTime = LocalDateTime.now()

    private var lastAccessedAt: LocalDateTime = LocalDateTime.now()

    override fun getSessionId(): String {
        lastAccessedAt = LocalDateTime.now()
        return sessionId
    }

    override fun getUserMailAddress(): String = userMailAddress

    override fun getCoupleNickname(): String = coupleNickname

    override fun getCreatedAt(): LocalDateTime = createdAt

    override fun getLastAccessedAt(): LocalDateTime = lastAccessedAt

    override fun isExistsInContext(): Boolean =
        sm.getSessionById(sessionId) != null
}