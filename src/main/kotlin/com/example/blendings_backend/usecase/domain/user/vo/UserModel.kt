package com.example.blendings_backend.usecase.domain.user.vo

import java.time.LocalDate
import java.util.*

data class UserModel(
    val name: String,
    val nickname: String? = null,
    val birthDate: LocalDate,
    val mailAddress: String,
    val password: String,
    val id: UUID? = null
)