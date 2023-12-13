package com.example.blendings_backend.domain.auth.presentation.dto.request

import com.example.blendings_backend.domain.auth.presentation.AuthValidationValue
import javax.validation.constraints.Pattern

data class SignRequest(

    @Pattern(regexp = AuthValidationValue.NAME_REGEXP, message = AuthValidationValue.NAME_MESSAGE)
    val maleName: String,

    @Pattern(regexp = AuthValidationValue.DATE_REGEXP, message = AuthValidationValue.DATE_MESSAGE)
    val maleBirthDay: String,

    @Pattern(regexp = AuthValidationValue.MAIL_REGEXP, message = AuthValidationValue.MAIL_MESSAGE)
    val maleMail: String,

    @Pattern(regexp = AuthValidationValue.PASSWORD_REGEXP, message = AuthValidationValue.PASSWORD_MESSAGE)
    val malePassword: String,

    @Pattern(regexp = AuthValidationValue.NAME_REGEXP, message = AuthValidationValue.NAME_MESSAGE)
    val femaleName: String,

    @Pattern(regexp = AuthValidationValue.DATE_REGEXP, message = AuthValidationValue.DATE_MESSAGE)
    val femaleBirthDay: String,

    @Pattern(regexp = AuthValidationValue.MAIL_REGEXP, message = AuthValidationValue.MAIL_MESSAGE)
    val femaleMail: String,

    @Pattern(regexp = AuthValidationValue.PASSWORD_REGEXP, message = AuthValidationValue.PASSWORD_MESSAGE)
    val femalePassword: String,

    @Pattern(regexp = AuthValidationValue.DATE_REGEXP, message = AuthValidationValue.DATE_MESSAGE)
    val metDay: String,

    @Pattern(regexp = AuthValidationValue.COUPLE_NICKNAME_REGEXP, message = AuthValidationValue.COUPLE_NICKNAME_MESSAGE)
    val coupleNickname: String
)