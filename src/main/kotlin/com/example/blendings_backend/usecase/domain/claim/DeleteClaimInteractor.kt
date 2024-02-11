package com.example.blendings_backend.usecase.domain.claim

import com.example.blendings_backend.usecase.domain.claim.exception.CannotAccessClaimException
import com.example.blendings_backend.usecase.domain.claim.exception.ClaimNotFoundException
import com.example.blendings_backend.usecase.domain.claim.port.`in`.DeleteClaimUseCase
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.DeleteClaimPort
import com.example.blendings_backend.usecase.domain.claim.port.out.persistence.FindClaimPort
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.global.annotation.Interactor

@Interactor
class DeleteClaimInteractor(
    private val findClaimPort: FindClaimPort,
    private val deleteClaimPort: DeleteClaimPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : DeleteClaimUseCase {

    override fun deleteClaim(coupleNickname: String, id: Long) {
        val user = getCurrentUserPort.execute()

        val claim = findClaimPort.findById(id) ?: throw ClaimNotFoundException

        if (claim.user.id != user.id) throw CannotAccessClaimException

        deleteClaimPort.delete(claim)
    }
}