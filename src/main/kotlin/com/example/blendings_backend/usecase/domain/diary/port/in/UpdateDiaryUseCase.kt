package com.example.blendings_backend.usecase.domain.diary.port.`in`

import com.example.blendings_backend.usecase.domain.diary.dto.request.UpdateDiaryRequest
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse

interface UpdateDiaryUseCase {
    fun updateDiary(coupleNickname: String, updateDiaryRequest: UpdateDiaryRequest, id: Long): LocationKeyResponse
}