package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.presentation.AuthValidationValue
import javax.validation.constraints.Pattern

data class SendMailRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_REGEXP, message = AuthValidationValue.MALE_MAIL_MESSAGE)
    val maleMail: String,

    @Pattern(regexp = AuthValidationValue.MAIL_REGEXP, message = AuthValidationValue.FEMALE_MAIL_MESSAGE)
    val femaleMail: String
)