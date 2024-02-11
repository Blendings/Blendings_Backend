package com.example.blendings_backend.usecase.domain.claim

import com.example.blendings_backend.usecase.domain.claim.dto.response.ClaimDetailsResponse
import com.example.blendings_backend.usecase.domain.claim.dto.response.ClaimListResponse
import com.example.blendings_backend.usecase.domain.claim.port.`in`.FindClaimUseCase
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.FindClaimPort
import com.example.blendings_backend.usecase.global.annotation.Interactor
import com.example.blendings_backend.usecase.global.convertor.LocalDateConvertor
import org.springframework.data.domain.PageRequest

@Interactor
class FindClaimInteractor(
    private val findClaimPort: FindClaimPort
) : FindClaimUseCase {

    override fun findClaimList(coupleNickname: String, index: Int): ClaimListResponse = ClaimListResponse(
        findClaimPort.findByCoupleNicknamePageable(
            coupleNickname,
            PageRequest.of(index, 5),
        ).map {
            ClaimDetailsResponse(
                id = it.id!!,
                usedAt = it.usedAt,
                cost = it.cost,
                date = LocalDateConvertor.convertLocalDateToString(it.date),
                userNickname = it.user.nickname
            )
        })
}