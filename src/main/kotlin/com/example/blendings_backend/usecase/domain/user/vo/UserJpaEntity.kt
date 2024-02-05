package com.example.blendings_backend.usecase.domain.user.vo

import com.example.blendings_backend.usecase.global.entty.BaseUUIDEntity
import com.example.blendings_backend.usecase.global.consts.TableName
import java.time.LocalDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity(name = TableName.USER_TABLE_NAME)
class UserJpaEntity(
    name: String,
    nickname: String = name,
    birthDate: LocalDate,
    mailAddress: String,
    password: String,
    id: UUID? = null
) : BaseUUIDEntity(id) {

    @Column(name = "name", updatable = true, nullable = false)
    val name: String = name

    @Column(name = "nickname", updatable = true, nullable = false)
    val nickname: String = nickname

    @Column(name = "birth_date", updatable = false, nullable = false)
    val birthDate: LocalDate = birthDate

    @Column(name = "mail_address", unique = true, updatable = true, nullable = false)
    val mailAddress: String = mailAddress

    @Column(name = "password", updatable = true, nullable = false, length = 61)
    val password: String = password

    @OneToOne
    @JoinColumn(name = "couple_nickname", updatable = true, nullable = false)
    val coupleMapJpaEntity: CoupleMapJpaEntity? = null
}