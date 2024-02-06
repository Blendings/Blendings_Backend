package com.example.blendings_backend.infrastructure.security.session.manager

import com.example.blendings_backend.infrastructure.security.session.Session
import org.springframework.stereotype.Component

@Component
class SimpleSessionManager : SessionManager {

    private var threadSession: ThreadLocal<Session?> = ThreadLocal()

    protected companion object {
        var sessionContext: MutableMap<String, Session> = mutableMapOf()
    }

    override fun getSession(): Session? =
        threadSession.get()

    override fun setSession(session: Session?) {
        threadSession.set(session)
    }

    override fun addSession(session: Session) {
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
        sessionContext[sessionId]
}