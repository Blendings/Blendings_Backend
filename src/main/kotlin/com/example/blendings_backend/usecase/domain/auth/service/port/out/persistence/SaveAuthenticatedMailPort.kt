package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

import com.example.blendings_backend.domain.auth.service.vo.AuthenticatedMailAddressModel

interface SaveAuthenticatedMailPort {

    fun saveAuthenticatedMailAddress(domain: AuthenticatedMailAddressModel): AuthenticatedMailAddressModel
}