package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.presentation.AuthValidationValue
import javax.validation.constraints.Pattern

data class ResendMailRequest(

    @Pattern(regexp = AuthValidationValue.MAIL_REGEXP, message = AuthValidationValue.MAIL_MESSAGE)
    val mail: String
)