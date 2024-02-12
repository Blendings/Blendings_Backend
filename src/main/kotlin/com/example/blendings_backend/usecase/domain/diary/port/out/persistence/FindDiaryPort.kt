package com.example.blendings_backend.usecase.domain.diary.port.out.persistence

import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import org.springframework.data.domain.PageRequest

interface FindDiaryPort {
    fun findById(id: Long): DiaryJpaEntity?
    fun findAllByCoupleNicknamePageable(coupleNickname: String, pageRequest: PageRequest): List<DiaryJpaEntity>
}