package com.example.blendings_backend.usecase.domain.auth.port.`in`

import com.example.blendings_backend.usecase.domain.auth.dto.request.AuthenticateMailRequest

interface AuthenticateMailAddressUseCase {

    fun authenticateMailAddress(dto: AuthenticateMailRequest)
}