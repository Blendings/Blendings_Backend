package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.presentation.AuthValidationValue
import com.example.blendings_backend.domain.auth.service.dto.LoginRequest
import javax.validation.constraints.Pattern

data class LoginWebRequest(


    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val mailAddress: String,

    @Pattern(regexp = AuthValidationValue.PASSWORD_REGEXP, message = AuthValidationValue.PASSWORD_MESSAGE)
    val password: String
) {
    fun toDomainRequest() = LoginRequest(mailAddress, password)
}
