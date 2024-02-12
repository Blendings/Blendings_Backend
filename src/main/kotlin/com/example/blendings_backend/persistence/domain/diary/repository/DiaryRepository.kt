package com.example.blendings_backend.persistence.domain.diary.repository

import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.Repository

@org.springframework.stereotype.Repository
interface DiaryRepository : Repository<DiaryJpaEntity, Long?> {

    fun save(diaryJpaEntity: DiaryJpaEntity): DiaryJpaEntity

    fun findById(id: Long): DiaryJpaEntity?

    fun findAllByCoupleNickname(coupleNickname: String, pageable: Pageable): List<DiaryJpaEntity>

    fun delete(diaryJpaEntity: DiaryJpaEntity)
}