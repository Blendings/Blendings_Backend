package com.example.blendings_backend.presentation.domain.auth.dto.request

import com.example.blendings_backend.presentation.domain.auth.AuthValidationValue
import com.example.blendings_backend.presentation.global.ValidationValue
import com.example.blendings_backend.presentation.global.WebRequest
import com.example.blendings_backend.usecase.domain.auth.dto.request.SignInfoDto
import com.example.blendings_backend.usecase.domain.auth.dto.request.SignRequest
import javax.validation.constraints.Pattern

data class SignWebRequest(

    @Pattern(regexp = AuthValidationValue.NAME_REGEXP, message = AuthValidationValue.NAME_MESSAGE)
    val maleName: String,

    @Pattern(regexp = ValidationValue.DATE_REGEXP, message = ValidationValue.DATE_MESSAGE)
    val maleBirthDay: String,

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val maleMailAddress: String,

    @Pattern(regexp = AuthValidationValue.PASSWORD_REGEXP, message = AuthValidationValue.PASSWORD_MESSAGE)
    val malePassword: String,

    @Pattern(regexp = AuthValidationValue.NAME_REGEXP, message = AuthValidationValue.NAME_MESSAGE)
    val femaleName: String,

    @Pattern(regexp = ValidationValue.DATE_REGEXP, message = ValidationValue.DATE_MESSAGE)
    val femaleBirthDay: String,

    @Pattern(regexp = AuthValidationValue.MAIL_ADDRESS_REGEXP, message = AuthValidationValue.MAIL_ADDRESS_MESSAGE)
    val femaleMailAddress: String,

    @Pattern(regexp = AuthValidationValue.PASSWORD_REGEXP, message = AuthValidationValue.PASSWORD_MESSAGE)
    val femalePassword: String,

    @Pattern(regexp = ValidationValue.DATE_REGEXP, message = ValidationValue.DATE_MESSAGE)
    val metDay: String,

    @Pattern(regexp = AuthValidationValue.COUPLE_NICKNAME_REGEXP, message = AuthValidationValue.COUPLE_NICKNAME_MESSAGE)
    val coupleNickname: String
) : WebRequest<SignRequest> {
    override fun toDomainRequest(): SignRequest =
        SignRequest(
            maleSignInfo = SignInfoDto(maleName, maleBirthDay, maleMailAddress, malePassword),
            femaleSignInfo = SignInfoDto(femaleName, femaleBirthDay, femaleMailAddress, femalePassword),
            metDay, coupleNickname
        )
}