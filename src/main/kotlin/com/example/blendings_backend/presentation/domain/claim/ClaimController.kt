package com.example.blendings_backend.presentation.domain.claim

import com.example.blendings_backend.presentation.domain.claim.dto.request.CreateClaimWebRequest
import com.example.blendings_backend.presentation.domain.claim.dto.request.UpdateClaimWebRequest
import com.example.blendings_backend.presentation.global.ResponseEditor.setLocationHeader
import com.example.blendings_backend.presentation.global.ValidationValue
import com.example.blendings_backend.usecase.domain.claim.dto.response.ClaimListResponse
import com.example.blendings_backend.usecase.domain.claim.port.`in`.*
import com.example.blendings_backend.usecase.global.annotation.WebAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@RequestMapping("/claims")
@WebAdapter
class ClaimController(
    private val findClaimUseCase: FindClaimUseCase,
    private val createClaimUseCase: CreateClaimUseCase,
    private val updateClaimUseCase: UpdateClaimUseCase,
    private val deleteClaimUseCase: DeleteClaimUseCase,
    private val approveClaimUseCase: ApproveClaimUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{coupleNickname}")
    fun claimCreate(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        createClaimWebRequest: CreateClaimWebRequest,
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ) {
        val response = createClaimUseCase.createClaim(coupleNickname, createClaimWebRequest.toDomainRequest())

        httpServletResponse.setLocationHeader(httpServletRequest.requestURL.toString(), response.key)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{coupleNickname}/{id}")
    fun claimUpdate(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        updateClaimWebRequest: UpdateClaimWebRequest,
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @Positive(message = ValidationValue.POSITIVE_MESSAGE)
        @PathVariable
        id: Long,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ) {
        val response = updateClaimUseCase.updateClaim(updateClaimWebRequest.toDomainRequest(), coupleNickname, id)

        httpServletResponse.setLocationHeader(httpServletRequest.requestURL.toString(), response.key)
    }

    @ResponseStatus(HttpStatus.RESET_CONTENT)
    @DeleteMapping("/{coupleNickname}/{id}")
    fun claimDelete(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @Positive(message = ValidationValue.POSITIVE_MESSAGE)
        @PathVariable
        id: Long
    ) {
        deleteClaimUseCase.deleteClaim(coupleNickname, id)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{coupleNickname}/{id}")
    fun claimApprove(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @Positive(message = ValidationValue.POSITIVE_MESSAGE)
        @PathVariable
        id: Long
    ) {
        approveClaimUseCase.approveClaim(coupleNickname, id)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{coupleNickname}")
    fun claimList(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @RequestParam
        index: Int
    ): ClaimListResponse =
        findClaimUseCase.findClaimList(coupleNickname, index)
}