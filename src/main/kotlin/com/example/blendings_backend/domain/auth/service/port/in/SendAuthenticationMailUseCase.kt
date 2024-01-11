package com.example.blendings_backend.domain.auth.service.port.`in`

import com.example.blendings_backend.domain.auth.service.dto.BinaryMailAddressDto

interface SendAuthenticationMailUseCase {

    fun sendAuthenticationMailsToCouple(dto: BinaryMailAddressDto)
}