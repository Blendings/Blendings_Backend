package com.example.blendings_backend.usecase.domain.diary

import com.example.blendings_backend.usecase.domain.diary.dto.request.UpdateDiaryRequest
import com.example.blendings_backend.usecase.domain.diary.exception.CannotAccessDiaryException
import com.example.blendings_backend.usecase.domain.diary.exception.DiaryNotFoundException
import com.example.blendings_backend.usecase.domain.diary.port.`in`.UpdateDiaryUseCase
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.FindDiaryPort
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.SaveDiaryPort
import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import com.example.blendings_backend.usecase.domain.user.exception.CannotAccessCoupleException
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.global.annotation.Interactor
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse

@Interactor
class UpdateDiaryInteractor(
    private val saveDiaryPort: SaveDiaryPort,
    private val findDiaryPort: FindDiaryPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : UpdateDiaryUseCase {

    override fun updateDiary(
        coupleNickname: String,
        updateDiaryRequest: UpdateDiaryRequest,
        id: Long
    ): LocationKeyResponse {
        val user = getCurrentUserPort.execute()
        val diary = findDiaryPort.findById(id) ?: throw DiaryNotFoundException

        if (diary.coupleNickname != coupleNickname)
            throw CannotAccessCoupleException

        if (diary.user.id != user.id)
            throw CannotAccessDiaryException

        updateDiaryRequest.run {
            saveDiaryPort.save(
                DiaryJpaEntity(
                    emotion = emotion,
                    content = content,
                    date = diary.date,
                    user = diary.user,
                    coupleNickname = diary.coupleNickname,
                    id = diary.id
                )
            )
        }

        return LocationKeyResponse(id)
    }
}