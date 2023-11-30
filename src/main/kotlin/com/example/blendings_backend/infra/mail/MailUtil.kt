package com.example.blendings_backend.infra.mail

interface MailUtil {

    fun sendMail(mail: String, subject: String, content: String)
}