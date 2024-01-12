package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.presentation.AuthValidationValue
import com.example.blendings_backend.domain.auth.service.dto.ResendMailRequest
import javax.validation.constraints.Pattern

data class ResendMailWebRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val mailAddress: String
) {
    fun toDomainRequest() = ResendMailRequest(mailAddress)
}
