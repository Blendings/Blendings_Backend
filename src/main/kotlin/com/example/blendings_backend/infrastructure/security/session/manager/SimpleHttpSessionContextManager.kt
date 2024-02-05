package com.example.blendings_backend.infrastructure.security.session.manager

import com.example.blendings_backend.infrastructure.security.session.manager.HttpSessionContextManager.Companion.MAIL_ADDRESS_ATTRIBUTE_KEY
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSession

@Component
class SimpleHttpSessionContextManager : HttpSessionContextManager {

    protected companion object {
        var sessionContext: MutableMap<String, HttpSession> = mutableMapOf()
    }

    override fun addSession(httpSession: HttpSession) {
        sessionContext[
            httpSession.getAttribute(MAIL_ADDRESS_ATTRIBUTE_KEY)?.toString()
                ?: throwSessionAttributeIntegrityBrokenException()
        ] = httpSession
    }

    override fun removeSession(httpSession: HttpSession) {
        sessionContext.remove(
            httpSession.getAttribute(MAIL_ADDRESS_ATTRIBUTE_KEY)?.toString()
                ?: throwSessionAttributeIntegrityBrokenException()
        )
    }

    override fun removeSessionByUserMailAddress(mailAddress: String) {
        sessionContext.remove(mailAddress)
    }

    override fun getSessionCount(): Long =
        sessionContext.size / 2L

    override fun getSessionContext(): MutableMap<String, HttpSession> =
        sessionContext.toMutableMap()

    override fun getSessionByUserMailAddress(mailAddress: String): HttpSession? =
        sessionContext[mailAddress]

    private fun throwSessionAttributeIntegrityBrokenException(): Nothing =
        throw RuntimeException(
            "Session's integrity was BROKEN." +
                    "Session's attribute not exists."
        )
}