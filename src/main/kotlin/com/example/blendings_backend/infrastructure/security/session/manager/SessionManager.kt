package com.example.blendings_backend.infrastructure.security.session.manager

import com.example.blendings_backend.infrastructure.security.session.Session

interface SessionManager {

    companion object {
        const val SESSION_ID_COOKIE_NAME = "USESSIONID"
        const val SESSION_EXPIRE_TIME = 1800
    }

    fun getCurrentSession(): Session?

    fun setCurrentSession(session: Session?)

    fun addSession(session: Session)

    fun removeSession(session: Session)

    fun removeSessionById(sessionId: String)

    fun getSessionCount(): Int

    fun getSessionContext(): MutableMap<String, Session>

    fun getSessionById(sessionId: String): Session?
}