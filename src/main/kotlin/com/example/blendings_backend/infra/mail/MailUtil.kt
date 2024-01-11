package com.example.blendings_backend.infra.mail

interface MailUtil {

    fun sendMail(mailAddress: String, subject: String, content: String)
}