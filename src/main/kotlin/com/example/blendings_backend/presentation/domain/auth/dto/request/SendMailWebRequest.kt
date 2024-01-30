package com.example.blendings_backend.presentation.domain.auth.dto.request

import com.example.blendings_backend.presentation.domain.auth.AuthValidationValue
import com.example.blendings_backend.usecase.domain.auth.service.dto.SendMailRequest
import javax.validation.constraints.Pattern

data class SendMailWebRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val maleMailAddress: String,

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val femaleMailAddress: String
) {
    fun toDomainRequest() = SendMailRequest(maleMailAddress, femaleMailAddress)
}