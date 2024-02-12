package com.example.blendings_backend.usecase.domain.diary.dto.response

data class DiarySemiDetailResponse(
    val id: Long,
    val emotion: String,
    val userNickname: String,
    val date: String
)
