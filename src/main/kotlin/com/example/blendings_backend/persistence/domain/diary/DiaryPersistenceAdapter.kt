package com.example.blendings_backend.persistence.domain.diary

import com.example.blendings_backend.persistence.domain.diary.repository.DiaryRepository
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.DeleteDiaryPort
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.FindDiaryPort
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.SaveDiaryPort
import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import org.springframework.data.domain.PageRequest

class DiaryPersistenceAdapter(
    private val diaryRepository: DiaryRepository
) : SaveDiaryPort,
    FindDiaryPort,
    DeleteDiaryPort {

    override fun save(diaryJpaEntity: DiaryJpaEntity): DiaryJpaEntity =
        diaryRepository.save(diaryJpaEntity)

    override fun findById(id: Long): DiaryJpaEntity? =
        diaryRepository.findById(id)

    override fun findAllByCoupleNicknamePageable(
        coupleNickname: String,
        pageRequest: PageRequest
    ): List<DiaryJpaEntity> =
        diaryRepository.findAllByCoupleNickname(coupleNickname, pageRequest)

    override fun delete(diaryJpaEntity: DiaryJpaEntity) {
        diaryRepository.delete(diaryJpaEntity)
    }
}