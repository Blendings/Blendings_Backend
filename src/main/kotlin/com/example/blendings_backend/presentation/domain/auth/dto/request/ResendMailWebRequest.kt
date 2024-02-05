package com.example.blendings_backend.presentation.domain.auth.dto.request

import com.example.blendings_backend.presentation.domain.auth.AuthValidationValue
import com.example.blendings_backend.presentation.global.WebRequest
import com.example.blendings_backend.usecase.domain.auth.service.dto.ResendMailRequest
import javax.validation.constraints.Pattern

data class ResendMailWebRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val mailAddress: String
) : WebRequest<ResendMailRequest> {
    override fun toDomainRequest(): ResendMailRequest =
        ResendMailRequest(mailAddress)
}
