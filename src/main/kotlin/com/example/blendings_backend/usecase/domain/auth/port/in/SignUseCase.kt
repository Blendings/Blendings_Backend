package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.SignRequest

interface SignUseCase {

    fun sign(dto: SignRequest)
}