package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.presentation.AuthValidationValue
import com.example.blendings_backend.domain.auth.service.dto.AuthenticateMailRequest
import javax.validation.constraints.Pattern

data class AuthenticateMailWebRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val mailAddress: String,

    @Pattern(
        regexp = AuthValidationValue.AUTHENTICATION_CODE_REGEXP,
        message = AuthValidationValue.AUTHENTICATION_CODE_MESSAGE
    )
    val authenticationCode: String
) {
    fun toDomainRequest() = AuthenticateMailRequest(mailAddress, authenticationCode)
}