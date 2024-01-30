package com.example.blendings_backend.presentation.domain.auth.dto.request

import com.example.blendings_backend.presentation.domain.auth.AuthValidationValue
import com.example.blendings_backend.usecase.domain.auth.service.dto.LoginRequest
import javax.validation.constraints.Pattern

data class LoginWebRequest(


    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val mailAddress: String,

    @Pattern(regexp = AuthValidationValue.PASSWORD_REGEXP, message = AuthValidationValue.PASSWORD_MESSAGE)
    val password: String
) {
    fun toDomainRequest() = LoginRequest(mailAddress, password)
}
