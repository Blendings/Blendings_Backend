package com.example.blendings_backend.domain.user.persistence

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
open class UserJpaEntity(
    name: String,
    birthDate: LocalDate,
    mail: String,
    password: String,
    id: UUID
) {
    @Column(name = "name", updatable = true, nullable = false)
    var name: String = name

    @Column(name = "nickname", updatable = true, nullable = true)
    var nickname: String? = null

    @Column(name = "birth_date", updatable = false, nullable = false)
    val birthDate: LocalDate = birthDate

    @Column(name = "mail_address", unique = true, updatable = true, nullable = false)
    var mailAddress: String = mail
        protected set

    @Column(name = "password", updatable = true, nullable = false, length = 61)
    var password: String = password
        protected set

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    val id: UUID = id
}