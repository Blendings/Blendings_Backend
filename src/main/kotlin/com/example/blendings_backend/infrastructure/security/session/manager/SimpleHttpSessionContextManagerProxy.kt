package com.example.blendings_backend.infrastructure.security.session.manager

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSession

@Primary
@Component
class SimpleHttpSessionContextManagerProxy : SimpleHttpSessionContextManager() {

    private companion object {
        lateinit var sessionContextSave: MutableMap<String, HttpSession>
    }

    override fun addSession(httpSession: HttpSession) {
        try {
            super.addSession(httpSession)
            commit()
        } catch (e: Exception) {
            rollback()
            throw e
        }
    }

    override fun removeSession(httpSession: HttpSession) {
        try {
            super.removeSession(httpSession)
            commit()
        } catch (e: Exception) {
            rollback()
            throw e
        }
    }

    override fun removeSessionByUserMailAddress(mailAddress: String) {
        try {
            super.removeSessionByUserMailAddress(mailAddress)
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