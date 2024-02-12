package com.example.blendings_backend.usecase.domain.diary.port.`in`

import com.example.blendings_backend.usecase.domain.diary.dto.response.DiaryDetailResponse

interface GetDiaryDetailUseCase {
    fun getDiaryDetail(coupleNickname: String, id: Long): DiaryDetailResponse
}