package com.example.blendings_backend.usecase.domain.diary.port.`in`

import com.example.blendings_backend.usecase.domain.diary.dto.request.WriteDiaryRequest
import com.example.blendings_backend.usecase.global.dto.response.LocationKeyResponse

interface WriteDiaryUseCase {
    fun writeDiary(coupleNickname: String, writeDiaryRequest: WriteDiaryRequest): LocationKeyResponse
}