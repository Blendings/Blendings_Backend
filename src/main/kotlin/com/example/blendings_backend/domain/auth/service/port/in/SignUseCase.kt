package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.SignRequest

interface SignUseCase {

    fun sign(dto: SignRequest)
}