package com.example.blendings_backend.usecase.domain.diary.port.`in`

import com.example.blendings_backend.usecase.domain.diary.dto.response.DiaryListResponse

interface GetDiaryListUseCase {
    fun getDiaryList(coupleNickname: String, index: Int): DiaryListResponse
}