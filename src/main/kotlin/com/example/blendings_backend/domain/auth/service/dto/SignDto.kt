package com.example.blendings_backend.domain.auth.service.dto

data class SignDto(
    val maleSignInfo: SignInfoDto,
    val femaleSignInfo: SignInfoDto,
    val metDay: String,
    val coupleNickname: String
)

data class SignInfoDto(
    val name: String,
    val birthDay: String,
    val mail: String,
    val password: String
)