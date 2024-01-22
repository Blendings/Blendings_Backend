package com.example.blendings_backend.domain.user.persistence.entity

import com.example.blendings_backend.domain.base.entty.BaseUUIDEntity
import com.example.blendings_backend.global.consts.TableName
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity(name = TableName.USER_TABLE_NAME)
class UserJpaEntity(
    name: String,
    nickname: String? = null,
    birthDate: LocalDate,
    mail: String,
    password: String,
    id: UUID? = null
) : BaseUUIDEntity(id) {

    @Column(name = "name", updatable = true, nullable = false)
    val name: String = name

    @Column(name = "nickname", updatable = true, nullable = true)
    val nickname: String? = nickname

    @Column(name = "birth_date", updatable = false, nullable = false)
    val birthDate: LocalDate = birthDate

    @Column(name = "mail_address", unique = true, updatable = true, nullable = false)
    val mailAddress: String = mail

    @Column(name = "password", updatable = true, nullable = false, length = 61)
    val password: String = password
}