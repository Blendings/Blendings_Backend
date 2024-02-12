package com.example.blendings_backend.usecase.domain.diary.port.`in`

interface DeleteDiaryUseCase {
    fun delete(coupleNickname: String, id: Long)
}