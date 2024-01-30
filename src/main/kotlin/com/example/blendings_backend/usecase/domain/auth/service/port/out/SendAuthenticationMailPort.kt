package com.example.blendings_backend.usecase.domain.auth.service.port.out

interface SendAuthenticationMailPort {

    fun sendAuthenticationMail(mailAddress: String, code: String)
}