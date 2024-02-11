package com.example.blendings_backend.usecase.domain.claim

import com.example.blendings_backend.usecase.domain.claim.exception.CannotAccessClaimException
import com.example.blendings_backend.usecase.domain.claim.exception.ClaimNotFoundException
import com.example.blendings_backend.usecase.domain.claim.port.`in`.ApproveClaimUseCase
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.FindClaimPort
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.SaveClaimPort
import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.global.annotation.Interactor

@Interactor
class ApproveClaimInteractor(
    private val saveClaimPort: SaveClaimPort,
    private val findClaimPort: FindClaimPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : ApproveClaimUseCase {

    override fun approveClaim(coupleNickname: String, id: Long) {
        val user = getCurrentUserPort.execute()

        val claim = findClaimPort.findById(id) ?: throw ClaimNotFoundException

        if (claim.user.id == user.id) throw CannotAccessClaimException

        claim.run {
            saveClaimPort.saveClaim(
                ClaimJpaEntity(
                    usedAt = usedAt,
                    cost = cost,
                    date = date,
                    user = this.user,
                    coupleNickname = this.coupleNickname,
                    isApproved = !isApproved,
                    id = this.id
                )
            )
        }
    }
}