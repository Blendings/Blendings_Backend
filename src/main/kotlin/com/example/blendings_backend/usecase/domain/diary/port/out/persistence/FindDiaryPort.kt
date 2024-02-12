package com.example.blendings_backend.usecase.domain.diary.port.out.persistence

import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import org.springframework.data.domain.PageRequest
import java.time.LocalDate

interface FindDiaryPort {
    fun findById(id: Long): DiaryJpaEntity?
    fun findByUserAndDate(user: UserJpaEntity, date: LocalDate): DiaryJpaEntity?
    fun findAllByCoupleNicknamePageable(coupleNickname: String, pageRequest: PageRequest): List<DiaryJpaEntity>
}