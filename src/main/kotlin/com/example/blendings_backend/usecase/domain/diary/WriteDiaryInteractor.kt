package com.example.blendings_backend.usecase.domain.diary

import com.example.blendings_backend.usecase.domain.diary.dto.request.WriteDiaryRequest
import com.example.blendings_backend.usecase.domain.diary.exception.CannotWriteManyDiaryInDayException
import com.example.blendings_backend.usecase.domain.diary.port.`in`.WriteDiaryUseCase
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.FindDiaryPort
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.SaveDiaryPort
import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.global.annotation.Interactor
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse
import java.time.LocalDate

@Interactor
class WriteDiaryInteractor(
    private val saveDiaryPort: SaveDiaryPort,
    private val findDiaryPort: FindDiaryPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : WriteDiaryUseCase {

    override fun writeDiary(coupleNickname: String, writeDiaryRequest: WriteDiaryRequest): LocationKeyResponse {
        val user = getCurrentUserPort.execute()
        val currentDate = LocalDate.now()

        if (findDiaryPort.findByUserAndDate(user, currentDate) != null)
            throw CannotWriteManyDiaryInDayException

        val diary = writeDiaryRequest.run {
            saveDiaryPort.save(
                DiaryJpaEntity(
                    emotion = emotion,
                    content = content,
                    date = currentDate,
                    user = user,
                    coupleNickname = coupleNickname
                )
            )
        }

        return LocationKeyResponse(diary.id!!)
    }
}