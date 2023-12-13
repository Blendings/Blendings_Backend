package com.example.blendings_backend.domain.user.service.dao

import java.time.LocalDate
import java.util.*

data class UserModel(
    val name: String,
    val birthDay: LocalDate,
    val mail: String,
    val password: String,
    val id: UUID
)