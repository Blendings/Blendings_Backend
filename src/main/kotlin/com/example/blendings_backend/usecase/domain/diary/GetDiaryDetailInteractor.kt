package com.example.blendings_backend.usecase.domain.diary

import com.example.blendings_backend.usecase.domain.diary.dto.response.DiaryDetailResponse
import com.example.blendings_backend.usecase.domain.diary.exception.DiaryNotFoundException
import com.example.blendings_backend.usecase.domain.diary.port.`in`.GetDiaryDetailUseCase
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.FindDiaryPort
import com.example.blendings_backend.usecase.domain.user.exception.CannotAccessCoupleException
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.global.annotation.ReadInteractor
import com.example.blendings_backend.usecase.global.convertor.LocalDateConvertor

@ReadInteractor
class GetDiaryDetailInteractor(
    private val findDiaryPort: FindDiaryPort,
    private val getCurrentUserPort: GetCurrentUserPort
) : GetDiaryDetailUseCase {

    override fun getDiaryDetail(coupleNickname: String, id: Long): DiaryDetailResponse {
        val user = getCurrentUserPort.execute()

        val diary = findDiaryPort.findById(id) ?: throw DiaryNotFoundException

        if (diary.coupleNickname != coupleNickname)
            throw CannotAccessCoupleException

        diary.run {
            return DiaryDetailResponse(
                emotion = emotion,
                content = content,
                userNickname = user.nickname,
                date = LocalDateConvertor.convertLocalDateToString(date)
            )
        }
    }
}