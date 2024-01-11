package com.example.blendings_backend.domain.user.persistence

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity()
open class UserJpaEntity(
    name: String,
    birthDate: LocalDate,
    mail: String,
    password: String,
    id: UUID
) {
    @Column(name = "name", nullable = false)
    var name: String = name

    @Column(name = "nickname", nullable = true)
    var nickname: String? = null

    @Column(name = "birth_date", nullable = false)
    val birthDate: LocalDate = birthDate

    @Column(name = "mail_address", unique = true, nullable = false)
    var mailAddress: String = mail
        protected set

    @Column(name = "password", nullable = false, length = 61)
    var password: String = password
        protected set

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)", nullable = false)
    val id: UUID = id
}