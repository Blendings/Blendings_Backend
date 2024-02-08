package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.request.SignRequest

interface SignUseCase {

    fun sign(dto: SignRequest)
}