package com.example.blendings_backend.infrastructure.mail

import com.example.blendings_backend.domain.auth.service.port.out.SendAuthenticationMailPort
import com.example.blendings_backend.usecase.global.annotation.Adapter
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine

@Adapter
class SendAuthenticationMailAdapter(
    private val mailUtil: MailUtil,
    private val templateEngine: SpringTemplateEngine
) : SendAuthenticationMailPort {

    private companion object {
        const val mailSubject = "[ 블렌딩 ] 인증 코드"
    }

    override fun sendAuthenticationMail(mailAddress: String, code: String) {
        mailUtil.sendMail(mailAddress, mailSubject, containCodeInBaseHtml(code))
    }

    private fun containCodeInBaseHtml(code: String): String {
        val context = Context()
        context.setVariable("code", code)
        return templateEngine.process("mail", context)
    }
}