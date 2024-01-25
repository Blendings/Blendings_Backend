package com.example.blendings_backend.infra.session.manager

import javax.servlet.http.HttpSession

interface HttpSessionContextManager {

    fun addSession(httpSession: HttpSession)

    fun removeSession(httpSession: HttpSession)

    fun removeSessionByUserMailAddress(mailAddress: String)

    fun getSessionCount(): Long

    fun getSessionContext(): MutableMap<String, HttpSession>

    fun getSessionByUserMailAddress(mailAddress: String): HttpSession?
}