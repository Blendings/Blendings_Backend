package com.example.blendings_backend.usecase.domain.diary.port.out.persistence

import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity

interface DeleteDiaryPort {
    fun delete(diaryJpaEntity: DiaryJpaEntity)
}