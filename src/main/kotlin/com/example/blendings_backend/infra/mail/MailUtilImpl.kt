package com.example.blendings_backend.infra.mail

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class MailUtilImpl(
    private val mailSender: JavaMailSender
) : MailUtil {

    override fun sendMail(mailAddress: String, subject: String, content: String) {
        val mimeMessage = mailSender.createMimeMessage()

        MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name()).run {
            setTo(mailAddress)
            setSubject(subject)
            setText(content, true)
        }

        mailSender.send(mimeMessage)
    }
}