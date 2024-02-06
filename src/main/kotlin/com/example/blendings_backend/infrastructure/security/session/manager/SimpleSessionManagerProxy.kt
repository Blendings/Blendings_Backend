package com.example.blendings_backend.infrastructure.security.session.manager

import com.example.blendings_backend.infrastructure.security.session.Session
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class SimpleSessionManagerProxy : SimpleSessionManager() {

    private companion object {
        var sessionContextSave: MutableMap<String, Session> = mutableMapOf()
    }

    @Synchronized
    override fun addSession(session: Session) {
        try {
            super.addSession(session)
            commit()
        } catch (e: Exception) {
            rollback()
            throw e
        }
    }

    @Synchronized
    override fun removeSession(session: Session) {
        try {
            super.removeSession(session)
            commit()
        } catch (e: Exception) {
            rollback()
            throw e
        }
    }

    override fun removeSessionById(sessionId: String) {
        try {
            super.removeSessionById(sessionId)
            commit()
        } catch (e: Exception) {
            rollback()
            throw e
        }
    }

    private fun commit() {
        sessionContextSave = sessionContext.toMutableMap()
    }

    private fun rollback() {
        sessionContext = sessionContextSave.toMutableMap()
    }
}