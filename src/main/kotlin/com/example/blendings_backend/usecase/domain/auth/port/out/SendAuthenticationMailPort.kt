package com.example.blendings_backend.usecase.domain.auth.port.out

interface SendAuthenticationMailPort {

    fun sendAuthenticationMail(mailAddress: String, code: String)
}