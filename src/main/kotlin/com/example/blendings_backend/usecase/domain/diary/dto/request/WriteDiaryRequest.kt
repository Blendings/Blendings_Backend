package com.example.blendings_backend.usecase.domain.diary.dto.request

data class WriteDiaryRequest(
    val emotion: String,
    val content: String
)
