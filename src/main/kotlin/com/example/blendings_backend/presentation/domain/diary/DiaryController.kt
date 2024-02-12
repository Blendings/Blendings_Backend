package com.example.blendings_backend.presentation.domain.diary

import com.example.blendings_backend.presentation.domain.diary.dto.request.UpdateDiaryWebRequest
import com.example.blendings_backend.presentation.domain.diary.dto.request.WriteDiaryWebRequest
import com.example.blendings_backend.presentation.global.ResponseEditor.setLocationHeader
import com.example.blendings_backend.presentation.global.ValidationValue
import com.example.blendings_backend.usecase.domain.diary.dto.response.DiaryDetailResponse
import com.example.blendings_backend.usecase.domain.diary.dto.response.DiaryListResponse
import com.example.blendings_backend.usecase.domain.diary.port.`in`.*
import com.example.blendings_backend.usecase.global.annotation.WebAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@RequestMapping("/diaries")
@WebAdapter
class DiaryController(
    private val writeDiaryUseCase: WriteDiaryUseCase,
    private val updateDiaryUseCase: UpdateDiaryUseCase,
    private val deleteDiaryUseCase: DeleteDiaryUseCase,
    private val getDiaryDetailUseCase: GetDiaryDetailUseCase,
    private val getDiaryListUseCase: GetDiaryListUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{coupleNickname}")
    fun diaryWrite(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @NotNull(message = ValidationValue.NOT_NULL_MESSAGE)
        @RequestBody
        writeDiaryWebRequest: WriteDiaryWebRequest,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ) {
        val response = writeDiaryUseCase.writeDiary(coupleNickname, writeDiaryWebRequest.toDomainRequest())

        httpServletResponse.setLocationHeader(httpServletRequest.requestURL.toString(), response.key)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{coupleNickname}/{id}")
    fun diaryUpdate(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @NotNull(message = ValidationValue.NOT_NULL_MESSAGE)
        @Positive(message = ValidationValue.POSITIVE_MESSAGE)
        @PathVariable
        id: Long,
        @NotNull(message = ValidationValue.NOT_NULL_MESSAGE)
        @RequestBody
        updateDiaryWebRequest: UpdateDiaryWebRequest,
        httpServletRequest: HttpServletRequest,
        httpServletResponse: HttpServletResponse
    ) {
        updateDiaryUseCase.updateDiary(coupleNickname, updateDiaryWebRequest.toDomainRequest(), id)

        httpServletResponse.setLocationHeader(httpServletRequest.requestURL.toString())
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{coupleNickname}/{id}")
    fun diaryDelete(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @NotNull(message = ValidationValue.NOT_NULL_MESSAGE)
        @Positive(message = ValidationValue.POSITIVE_MESSAGE)
        @PathVariable
        id: Long
    ) {
        deleteDiaryUseCase.delete(coupleNickname, id)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{coupleNickname}/{id}")
    fun diaryDetailGet(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @NotNull(message = ValidationValue.NOT_NULL_MESSAGE)
        @Positive(message = ValidationValue.POSITIVE_MESSAGE)
        @PathVariable
        id: Long
    ): DiaryDetailResponse =
        getDiaryDetailUseCase.getDiaryDetail(coupleNickname, id)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{coupleNickname}")
    fun diaryListGet(
        @NotBlank(message = ValidationValue.NOT_BLANK_MESSAGE)
        @PathVariable
        coupleNickname: String,
        @PositiveOrZero(message = ValidationValue.POSITIVE_OR_ZERO_MESSAGE)
        @RequestParam
        index: Int
    ): DiaryListResponse =
        getDiaryListUseCase.getDiaryList(coupleNickname, index)
}