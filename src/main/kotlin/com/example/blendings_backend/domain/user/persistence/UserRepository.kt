package com.example.blendings_backend.domain.user.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserJpaEntity, UUID> {

    fun existsByMailAddress(mail: String): Boolean
}