package com.example.blendings_backend.infrastructure.mail

interface MailUtil {

    fun sendMail(mailAddress: String, subject: String, content: String)
}