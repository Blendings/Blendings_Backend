package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.AuthenticateMailAddressDto

interface AuthenticateMailAddressUseCase {

    fun authenticateMailAddress(dto: AuthenticateMailAddressDto)
}