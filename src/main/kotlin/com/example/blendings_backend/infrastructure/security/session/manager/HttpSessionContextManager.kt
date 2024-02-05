package com.example.blendings_backend.infrastructure.security.session.manager

import javax.servlet.http.HttpSession

interface HttpSessionContextManager {

    companion object {
        const val MAIL_ADDRESS_ATTRIBUTE_KEY = "mailAddress"
        const val COUPLE_NICKNAME_ATTRIBUTE_KEY = "coupleNickname"
    }

    fun addSession(httpSession: HttpSession)

    fun removeSession(httpSession: HttpSession)

    fun removeSessionByUserMailAddress(mailAddress: String)

    fun getSessionCount(): Long

    fun getSessionContext(): MutableMap<String, HttpSession>

    fun getSessionByUserMailAddress(mailAddress: String): HttpSession?
}