package com.example.blendings_backend.domain.user.persistence

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
    @JoinColumn(name = "male_user_id", nullable = false)
    val maleUser: UserJpaEntity = maleUser

    @OneToOne
    @JoinColumn(name = "female_user_id", nullable = false)
    val femaleUser: UserJpaEntity = femaleUser

    @Column(name = "met_date", nullable = false)
    val metDate: LocalDate = metDate

    @Id
    @Column(name = "nickname", nullable = false, unique = true)
    val nickname: String = nickname
}