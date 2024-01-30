package com.example.blendings_backend.usecase.domain.user.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity

interface SaveUserPort {

    fun saveUser(userJpaEntity: UserJpaEntity): UserJpaEntity
}