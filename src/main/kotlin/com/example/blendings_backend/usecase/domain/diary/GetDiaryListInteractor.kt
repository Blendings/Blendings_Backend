package com.example.blendings_backend.usecase.domain.diary

import com.example.blendings_backend.usecase.domain.diary.dto.response.DiaryListResponse
import com.example.blendings_backend.usecase.domain.diary.dto.response.DiarySemiDetailResponse
import com.example.blendings_backend.usecase.domain.diary.port.`in`.GetDiaryListUseCase
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.FindDiaryPort
import com.example.blendings_backend.usecase.global.annotation.ReadInteractor
import com.example.blendings_backend.usecase.global.convertor.LocalDateConvertor
import org.springframework.data.domain.PageRequest

@ReadInteractor
class GetDiaryListInteractor(
    private val findDiaryPort: FindDiaryPort
) : GetDiaryListUseCase {

    override fun getDiaryList(coupleNickname: String, index: Int): DiaryListResponse = DiaryListResponse(
        findDiaryPort.findAllByCoupleNicknamePageable(coupleNickname, PageRequest.of(index, 5)).map {
            DiarySemiDetailResponse(
                id = it.id!!,
                emotion = it.emotion,
                userNickname = it.user.nickname,
                date = LocalDateConvertor.convertLocalDateToString(it.date)
            )
        })
}