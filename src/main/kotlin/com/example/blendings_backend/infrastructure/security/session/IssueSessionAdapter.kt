package com.example.blendings_backend.infrastructure.security.session

import com.example.blendings_backend.infrastructure.security.session.manager.HttpSessionContextManager
import com.example.blendings_backend.presentation.global.port.out.IssueSessionPort
import com.example.blendings_backend.usecase.global.annotation.Adapter
import javax.servlet.http.HttpServletRequest

@Adapter
class IssueSessionAdapter(
    private val sm: HttpSessionContextManager
) : IssueSessionPort {

    override fun execute(httpServletRequest: HttpServletRequest, userMailAddress: String, coupleNickname: String) {
        httpServletRequest.getSession(false)?.invalidate()

        val session = httpServletRequest.session
        session.run {
            setAttribute(HttpSessionContextManager.MAIL_ADDRESS_ATTRIBUTE_KEY, userMailAddress)
            setAttribute(HttpSessionContextManager.COUPLE_NICKNAME_ATTRIBUTE_KEY, coupleNickname)
        }

        sm.addSession(session)
    }
}