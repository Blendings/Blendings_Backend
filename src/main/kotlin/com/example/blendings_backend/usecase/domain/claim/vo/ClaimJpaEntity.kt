package com.example.blendings_backend.usecase.domain.claim.vo

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapJpaEntity
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.consts.TableName
import com.example.blendings_backend.usecase.global.entty.BaseLongIdEntity
import java.time.LocalDate
import javax.persistence.*

@Entity(name = TableName.CLAIM_TABLE_NAME)
class ClaimJpaEntity(
    usedAt: String,
    cost: Long,
    date: LocalDate,
    user: UserJpaEntity,
    couple: CoupleMapJpaEntity,
    isApproved: Boolean,
    id: Long? = null
) : BaseLongIdEntity(id) {

    @Column(name = "used_at", updatable = true, nullable = false)
    val usedAt: String = usedAt

    @Column(name = "cost", updatable = true, nullable = false)
    val cost: Long = cost

    @Column(name = "date", updatable = true, nullable = false)
    val date: LocalDate = date

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = true, nullable = false)
    val user: UserJpaEntity = user

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_nickname", updatable = false, nullable = false)
    val couple: CoupleMapJpaEntity = couple

    @Column(name = "is_approved", updatable = true, nullable = false)
    val isApproved: Boolean = isApproved
}