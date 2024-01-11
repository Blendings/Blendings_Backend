package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.AuthenticateMailRequest

interface AuthenticateMailAddressUseCase {

    fun authenticateMailAddress(dto: AuthenticateMailRequest)
}