package com.example.blendings_backend.usecase.domain.user.vo

import com.example.blendings_backend.usecase.domain.claim.vo.ClaimJpaEntity
import com.example.blendings_backend.usecase.domain.diary.vo.DiaryJpaEntity
import com.example.blendings_backend.usecase.global.consts.TableName
import com.example.blendings_backend.usecase.global.entty.BaseEntity
import java.time.LocalDate
import javax.persistence.*

@Entity(name = TableName.COUPLE_MAP_TABLE_NAME)
class CoupleMapJpaEntity(
    maleUser: UserJpaEntity,
    femaleUser: UserJpaEntity,
    metDate: LocalDate,
    nickname: String
) : BaseEntity<String?>() {

    @Id
    @Column(name = "nickname", unique = true, updatable = true, nullable = false)
    override var id: String? = nickname

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "male_user_id", unique = true, updatable = false, nullable = false)
    val maleUser: UserJpaEntity = maleUser

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "female_user_id", unique = true, updatable = false, nullable = false)
    val femaleUser: UserJpaEntity = femaleUser

    @Column(name = "met_date", updatable = false, nullable = false)
    val metDate: LocalDate = metDate

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    val claims: MutableList<ClaimJpaEntity> = mutableListOf()
        get() = field.toMutableList()

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    val diaries: MutableList<DiaryJpaEntity> = mutableListOf()
        get() = field.toMutableList()
}