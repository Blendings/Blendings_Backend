package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.presentation.AuthValidationValue
import javax.validation.constraints.Pattern

data class AuthenticateMailRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val mailAddress: String,

    @Pattern(
        regexp = AuthValidationValue.AUTHENTICATION_CODE_REGEXP,
        message = AuthValidationValue.AUTHENTICATION_CODE_MESSAGE
    )
    val authenticationCode: String
)