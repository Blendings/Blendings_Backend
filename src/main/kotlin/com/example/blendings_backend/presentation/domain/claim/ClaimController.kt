package com.example.blendings_backend.presentation.domain.claim

import com.example.blendings_backend.presentation.domain.claim.dto.request.CreateClaimWebRequest
import com.example.blendings_backend.presentation.global.ResponseEditor.setLocationHeader
import com.example.blendings_backend.presentation.global.ResponseEditor.setStatusCreated
import com.example.blendings_backend.usecase.domain.claim.port.`in`.CreateClaimUseCase
import com.example.blendings_backend.usecase.global.annotation.WebAdapter
import com.example.blendings_backend.presentation.global.ValidationValue
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RequestMapping("/claims")
@WebAdapter
class ClaimController(
    private val createClaimUseCase: CreateClaimUseCase
) {

    @PostMapping("/{coupleNickname}")
    fun claimCreate(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        createClaimWebRequest: CreateClaimWebRequest,
        @PathVariable
        coupleNickname: String,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ) {
        val response = createClaimUseCase.createClaim(coupleNickname, createClaimWebRequest.toDomainRequest())

        httpServletResponse.setStatusCreated().setLocationHeader(httpServletRequest.requestURL.toString(), response.key)
    }
}