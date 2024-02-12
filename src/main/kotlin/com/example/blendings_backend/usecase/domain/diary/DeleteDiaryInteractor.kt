package com.example.blendings_backend.usecase.domain.diary

import com.example.blendings_backend.usecase.domain.diary.exception.CannotAccessDiaryException
import com.example.blendings_backend.usecase.domain.diary.exception.DiaryNotFoundException
import com.example.blendings_backend.usecase.domain.diary.port.`in`.DeleteDiaryUseCase
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.DeleteDiaryPort
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.FindDiaryPort
import com.example.blendings_backend.usecase.domain.user.exception.CannotAccessCoupleException
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.global.annotation.Interactor

@Interactor
class DeleteDiaryInteractor(
    private val findDiaryPort: FindDiaryPort,
    private val deleteDiaryPort: DeleteDiaryPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : DeleteDiaryUseCase {

    override fun delete(coupleNickname: String, id: Long) {
        val user = getCurrentUserPort.execute()

        val diary = findDiaryPort.findById(id) ?: throw DiaryNotFoundException

        if (diary.coupleNickname != coupleNickname)
            throw CannotAccessCoupleException

        if (diary.user.id != user.id)
            throw CannotAccessDiaryException

        deleteDiaryPort.delete(diary)
    }
}