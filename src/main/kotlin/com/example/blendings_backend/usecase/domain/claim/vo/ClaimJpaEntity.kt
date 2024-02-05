package com.example.blendings_backend.usecase.domain.claim.vo

import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.consts.TableName
import com.example.blendings_backend.usecase.global.entty.BaseLongIdEntity
import java.time.LocalDate
import javax.persistence.*

@Entity(name = TableName.CLAIM_TABLE_NAME)
class ClaimJpaEntity(
    use: String,
    cost: Long,
    date: LocalDate,
    userJpaEntity: UserJpaEntity,
    isApproved: Boolean,
    id: Long? = null
) : BaseLongIdEntity(id) {

    @Column(name = "use", updatable = true, nullable = false)
    val use: String = use

    @Column(name = "cost", updatable = true, nullable = false)
    val cost: Long = cost

    @Column(name = "date", updatable = true, nullable = false)
    val date: LocalDate = date

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nickname", updatable = true, nullable = false)
    val userJpaEntity: UserJpaEntity = userJpaEntity

    @Column(name = "is_approved", updatable = true, nullable = false)
    val isApproved: Boolean = isApproved
}