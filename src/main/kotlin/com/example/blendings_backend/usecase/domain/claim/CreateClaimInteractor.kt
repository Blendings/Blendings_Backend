package com.example.blendings_backend.usecase.domain.claim

import com.example.blendings_backend.usecase.domain.auth.exception.MetDayAfterThanCurrentDayException
import com.example.blendings_backend.usecase.domain.claim.dto.request.CreateClaimRequest
import com.example.blendings_backend.usecase.domain.claim.port.`in`.CreateClaimUseCase
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.SaveClaimPort
import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.FindCoupleMapByNicknamePort
import com.example.blendings_backend.usecase.global.annotation.Interactor
import com.example.blendings_backend.usecase.global.convertor.LocalDateConvertor
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse
import java.time.LocalDate

@Interactor
class CreateClaimInteractor(
    private val saveClaimPort: SaveClaimPort,
    private val findCoupleMapByNicknamePort: FindCoupleMapByNicknamePort,
    private val getCurrentUserPort: GetCurrentUserPort
) : CreateClaimUseCase {

    override fun createClaim(coupleNickname: String, createClaimRequest: CreateClaimRequest): LocationKeyResponse {
        val user = getCurrentUserPort.execute()

        val localDate = LocalDateConvertor.convertStringToLocalDate(createClaimRequest.date)
        if (localDate.isAfter(LocalDate.now()))
            throw MetDayAfterThanCurrentDayException

        val couple = findCoupleMapByNicknamePort.findCoupleMapByNickname(coupleNickname)!!

        val claim = saveClaimPort.saveClaim(
            createClaimRequest.run {
                ClaimJpaEntity(
                    usedAt = usedAt,
                    cost = cost,
                    date = localDate,
                    user = user,
                    coupleNickname = couple.id!!,
                    isApproved = false
                )
            }
        )

        return LocationKeyResponse(claim.id!!)
    }
}