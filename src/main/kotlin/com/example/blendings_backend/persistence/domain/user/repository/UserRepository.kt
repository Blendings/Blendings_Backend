package com.example.blendings_backend.persistence.domain.user.repository

import com.example.blendings_backend.persistence.domain.user.UserJpaEntity
import org.springframework.data.repository.Repository
import java.util.*

interface UserRepository : Repository<UserJpaEntity, UUID> {

    fun save(userJpaEntity: UserJpaEntity): UserJpaEntity

    fun findById(id: UUID): UserJpaEntity?

    fun findByMailAddress(mail: String): UserJpaEntity?
}