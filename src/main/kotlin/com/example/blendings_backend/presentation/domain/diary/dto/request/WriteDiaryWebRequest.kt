package com.example.blendings_backend.presentation.domain.diary.dto.request

import com.example.blendings_backend.presentation.global.ValidationValue
import com.example.blendings_backend.presentation.global.WebRequest
import com.example.blendings_backend.usecase.domain.diary.dto.request.WriteDiaryRequest
import javax.validation.constraints.NotBlank

data class WriteDiaryWebRequest(

    @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
    val emotion: String,

    @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
    val content: String,
) : WebRequest<WriteDiaryRequest> {
    override fun toDomainRequest(): WriteDiaryRequest = WriteDiaryRequest(emotion, content)
}
