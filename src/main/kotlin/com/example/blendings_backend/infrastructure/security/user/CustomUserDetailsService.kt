package com.example.blendings_backend.infrastructure.security.user

import com.example.blendings_backend.domain.auth.service.exception.UnauthorizedException
import com.example.blendings_backend.persistence.domain.user.UserMapper
import com.example.blendings_backend.persistence.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        CustomUserDetails(
            UserMapper.toModel(
                userRepository.findById(UUID.fromString(username)) ?: throw UnauthorizedException
            )
        )
}