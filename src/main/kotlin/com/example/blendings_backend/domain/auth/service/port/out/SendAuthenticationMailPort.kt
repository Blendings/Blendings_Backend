package com.example.blendings_backend.domain.auth.service.port.out

interface SendAuthenticationMailPort {

    fun sendAuthenticationMail(mailAddress: String, code: String)
}