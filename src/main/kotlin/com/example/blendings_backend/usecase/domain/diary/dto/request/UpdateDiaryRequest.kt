package com.example.blendings_backend.usecase.domain.diary.dto.request

data class UpdateDiaryRequest(
    val emotion: String,
    val content: String
)
