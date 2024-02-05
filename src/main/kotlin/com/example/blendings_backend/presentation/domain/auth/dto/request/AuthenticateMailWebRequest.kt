package com.example.blendings_backend.presentation.domain.auth.dto.request

import com.example.blendings_backend.presentation.domain.auth.AuthValidationValue
import com.example.blendings_backend.presentation.global.WebRequest
import com.example.blendings_backend.usecase.domain.auth.service.dto.AuthenticateMailRequest
import javax.validation.constraints.Pattern

data class AuthenticateMailWebRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val mailAddress: String,

    @Pattern(
        regexp = AuthValidationValue.AUTHENTICATION_CODE_REGEXP,
        message = AuthValidationValue.AUTHENTICATION_CODE_MESSAGE
    )
    val authenticationCode: String
) : WebRequest<AuthenticateMailRequest> {
    override fun toDomainRequest(): AuthenticateMailRequest =
        AuthenticateMailRequest(mailAddress, authenticationCode)
}