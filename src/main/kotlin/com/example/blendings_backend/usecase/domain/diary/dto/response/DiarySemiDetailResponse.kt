package com.example.blendings_backend.usecase.domain.diary.dto.response

import java.time.LocalDate

data class DiarySemiDetailResponse(
    val emotion: String,
    val userNickname: String,
    val date: String
)
