package com.example.blendings_backend.domain.user.persistence.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
class CoupleMapJpaEntity(
    maleUser: UserJpaEntity,
    femaleUser: UserJpaEntity,
    metDate: LocalDate,
    nickname: String
) {
    @OneToOne
    @JoinColumn(name = "male_user_id", updatable = false, nullable = false)
    val maleUser: UserJpaEntity = maleUser

    @OneToOne
    @JoinColumn(name = "female_user_id", updatable = false, nullable = false)
    val femaleUser: UserJpaEntity = femaleUser

    @Column(name = "met_date", updatable = false, nullable = false)
    val metDate: LocalDate = metDate

    @Id
    @Column(name = "nickname", updatable = true, unique = true, nullable = false)
    val nickname: String = nickname
}