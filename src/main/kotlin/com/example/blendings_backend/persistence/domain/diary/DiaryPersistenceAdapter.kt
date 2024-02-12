package com.example.blendings_backend.persistence.domain.diary

import com.example.blendings_backend.persistence.domain.diary.repository.DiaryRepository
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.DeleteDiaryPort
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.FindDiaryPort
import com.example.blendings_backend.usecase.domain.diary.port.out.persistence.SaveDiaryPort
import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter
import org.springframework.data.domain.PageRequest
import java.time.LocalDate

@PersistenceAdapter
class DiaryPersistenceAdapter(
    private val diaryRepository: DiaryRepository
) : SaveDiaryPort,
    FindDiaryPort,
    DeleteDiaryPort {

    override fun save(diaryJpaEntity: DiaryJpaEntity): DiaryJpaEntity =
        diaryRepository.save(diaryJpaEntity)

    override fun findById(id: Long): DiaryJpaEntity? =
        diaryRepository.findById(id)

    override fun findByUserAndDate(user: UserJpaEntity, date: LocalDate): DiaryJpaEntity? =
        diaryRepository.findByUserAndDate(user, date)

    override fun findAllByCoupleNicknamePageable(
        coupleNickname: String,
        pageRequest: PageRequest
    ): List<DiaryJpaEntity> =
        diaryRepository.findAllByCoupleNickname(coupleNickname, pageRequest)

    override fun delete(diaryJpaEntity: DiaryJpaEntity) {
        diaryRepository.delete(diaryJpaEntity)
    }
}