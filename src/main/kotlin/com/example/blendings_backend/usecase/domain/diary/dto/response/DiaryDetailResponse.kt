package com.example.blendings_backend.usecase.domain.diary.dto.response

data class DiaryDetailResponse(
    val id: Long,
    val emotion: String,
    val content: String,
    val userNickname: String,
    val date: String
)
