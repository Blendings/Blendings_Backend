package com.example.blendings_backend.presentation.domain.claim.dto.request

import com.example.blendings_backend.presentation.global.ValidationValue
import com.example.blendings_backend.presentation.global.WebRequest
import com.example.blendings_backend.usecase.domain.claim.dto.request.CreateClaimRequest
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

data class CreateClaimWebRequest(

    @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
    val usedAt: String?,

    @NotNull(message = ValidationValue.NOT_NULL_MESSAGE)
    @Positive(message = ValidationValue.POSITIVE_MESSAGE)
    val cost: Long?,

    @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
    @Pattern(regexp = ValidationValue.DATE_REGEXP, message = ValidationValue.DATE_MESSAGE)
    val date: String?
) : WebRequest<CreateClaimRequest> {
    override fun toDomainRequest(): CreateClaimRequest =
        CreateClaimRequest(usedAt!!, cost!!, date!!)
}
