package com.example.blendings_backend.usecase.domain.user.port.out

import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity

interface GetCurrentUserPort {
    fun execute(): UserJpaEntity
}