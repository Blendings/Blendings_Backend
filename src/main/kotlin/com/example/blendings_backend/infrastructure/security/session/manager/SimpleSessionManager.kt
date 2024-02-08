package com.example.blendings_backend.infrastructure.security.session.manager

import com.example.blendings_backend.infrastructure.security.session.Session
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime

@Component
class SimpleSessionManager : SessionManager {

    private var threadSession: ThreadLocal<Session?> = ThreadLocal()

    protected companion object {
        var sessionContext: MutableMap<String, Session> = mutableMapOf()
    }

    override fun getCurrentSession(): Session? =
        threadSession.get()

    override fun setCurrentSession(session: Session?) {
        threadSession.set(session)
    }

    override fun addSession(session: Session) {
        sessionContext.forEach {
            if (it.value.getUserMailAddress() == session.getUserMailAddress()
                || createdAtExpired(it.value.getCreatedAt())
            ) removeSession(it.value)
        }
        sessionContext[session.getSessionId()] = session
    }

    override fun removeSession(session: Session) {
        sessionContext.remove(session.getSessionId())
    }

    override fun removeSessionById(sessionId: String) {
        sessionContext.remove(sessionId)
    }

    override fun getSessionCount(): Int =
        sessionContext.size

    override fun getSessionContext(): MutableMap<String, Session> =
        sessionContext.toMutableMap()

    override fun getSessionById(sessionId: String): Session? =
        sessionContext[sessionId]?.run {
            if (createdAtExpired(getCreatedAt())) {
                removeSession(this)
                null
            } else this
        }

    private fun createdAtExpired(createdAt: LocalDateTime): Boolean =
        Duration.between(createdAt, LocalDateTime.now()).seconds > SessionManager.SESSION_EXPIRE_TIME
}