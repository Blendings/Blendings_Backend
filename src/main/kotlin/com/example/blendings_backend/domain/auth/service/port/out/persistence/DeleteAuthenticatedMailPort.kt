package com.example.blendings_backend.domain.auth.service.port.out.persistence

import com.example.blendings_backend.domain.auth.service.vo.AuthenticatedMailAddressModel

interface DeleteAuthenticatedMailPort {

    fun deleteAuthenticatedMail(model: AuthenticatedMailAddressModel)
}