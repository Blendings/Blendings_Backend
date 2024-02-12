package com.example.blendings_backend.usecase.domain.diary.vo

import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.consts.TableName
import com.example.blendings_backend.usecase.global.entty.BaseLongIdEntity
import java.time.LocalDate
import javax.persistence.*

@Entity(name = TableName.DIARY_TABLE_NAME)
class DiaryJpaEntity(
    content: String,
    emotion: String,
    date: LocalDate,
    user: UserJpaEntity,
    coupleNickname: String,
    id: Long? = null
) : BaseLongIdEntity(id) {

    @Column(name = "content", updatable = true, nullable = false)
    val content: String = content

    @Column(name = "emotion", updatable = true, nullable = false)
    val emotion: String = emotion

    @Column(name = "date", updatable = false, nullable = false)
    val date: LocalDate = date

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    val user: UserJpaEntity = user

    @Column(name = "couple_nickname", updatable = true, nullable = false)
    val coupleNickname: String = coupleNickname
}