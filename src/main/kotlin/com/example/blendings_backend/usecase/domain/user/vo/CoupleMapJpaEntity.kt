package com.example.blendings_backend.usecase.domain.user.vo

import com.example.blendings_backend.usecase.global.consts.TableName
import java.time.LocalDate
import javax.persistence.*

@Entity(name = TableName.COUPLE_MAP_TABLE_NAME)
class CoupleMapJpaEntity(
    maleUser: UserJpaEntity,
    femaleUser: UserJpaEntity,
    metDate: LocalDate,
    nickname: String
) {

    @OneToOne
    @JoinColumn(name = "male_user_id", unique = true, updatable = false, nullable = false)
    val maleUser: UserJpaEntity = maleUser

    @OneToOne
    @JoinColumn(name = "female_user_id", unique = true, updatable = false, nullable = false)
    val femaleUser: UserJpaEntity = femaleUser

    @Column(name = "met_date", updatable = false, nullable = false)
    val metDate: LocalDate = metDate

    @Id
    @Column(name = "nickname", unique = true, updatable = true, nullable = false)
    val nickname: String = nickname
}