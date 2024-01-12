package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.service.dto.LoginRequest

data class LoginWebRequest(
    val mailAddress: String,
    val password: String
) {
    fun toDomainRequest() = LoginRequest(mailAddress, password)
}
