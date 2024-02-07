package com.example.blendings_backend.usecase.domain.claim

import com.example.blendings_backend.usecase.domain.auth.exception.MetDayAfterThanCurrentDayException
import com.example.blendings_backend.usecase.domain.claim.dto.request.UpdateClaimRequest
import com.example.blendings_backend.usecase.domain.claim.exception.CannotAccessClaimException
import com.example.blendings_backend.usecase.domain.claim.exception.ClaimNotFoundException
import com.example.blendings_backend.usecase.domain.claim.port.`in`.UpdateClaimUseCase
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.FindClaimPort
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.SaveClaimPort
import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import com.example.blendings_backend.usecase.domain.user.exception.CannotAccessCoupleException
import com.example.blendings_backend.usecase.domain.user.exception.CoupleNotFoundException
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.FindCoupleMapByNicknamePort
import com.example.blendings_backend.usecase.global.annotation.Interactor
import com.example.blendings_backend.usecase.global.convertor.LocalDateConvertor
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse
import java.time.LocalDate

@Interactor
class UpdateClaimInteractor(
    private val saveClaimPort: SaveClaimPort,
    private val findClaimPort: FindClaimPort,
    private val findCoupleMapByNicknamePort: FindCoupleMapByNicknamePort,
    private val getCurrentUserPort: GetCurrentUserPort
) : UpdateClaimUseCase {

    override fun updateClaim(
        updateClaimRequest: UpdateClaimRequest,
        coupleNickname: String,
        id: Long
    ): LocationKeyResponse {
        val user = getCurrentUserPort.execute()

        val localDate = LocalDateConvertor.convertStringToLocalDate(updateClaimRequest.date)
        if (localDate.isAfter(LocalDate.now()))
            throw MetDayAfterThanCurrentDayException

        val couple = findCoupleMapByNicknamePort.findCoupleMapByNickname(coupleNickname)
            ?: throw CoupleNotFoundException

        if (couple.femaleUser.id != user.id && couple.maleUser.id != user.id)
            throw CannotAccessCoupleException

        val claim = findClaimPort.findById(id) ?: throw ClaimNotFoundException

        if (claim.user.id != user.id)
            throw CannotAccessClaimException

        saveClaimPort.saveClaim(
            updateClaimRequest.run {
                ClaimJpaEntity(
                    usedAt = usedAt,
                    cost = cost,
                    date = localDate,
                    user = claim.user,
                    couple = claim.couple,
                    isApproved = false
                )
            }
        )

        return LocationKeyResponse(claim.id!!)
    }
}