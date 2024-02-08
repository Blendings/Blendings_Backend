package com.example.blendings_backend.usecase.domain.auth.dto.request

data class SignRequest(
    val maleSignInfo: SignInfoDto,
    val femaleSignInfo: SignInfoDto,
    val metDay: String,
    val coupleNickname: String
)

data class SignInfoDto(
    val name: String,
    val birthDay: String,
    val mailAddress: String,
    val password: String
)