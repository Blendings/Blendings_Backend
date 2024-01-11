package com.example.blendings_backend.domain.user.service.vo

import java.time.LocalDate

data class CoupleMapModel(
    val maleUser: UserModel,
    val femaleUser: UserModel,
    val metDate: LocalDate,
    val nickname: String
)