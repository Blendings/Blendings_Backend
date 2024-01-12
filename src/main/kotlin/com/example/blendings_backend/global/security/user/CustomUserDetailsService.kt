package com.example.blendings_backend.global.security.user

import com.example.blendings_backend.domain.auth.service.exception.UnauthorizedException
import com.example.blendings_backend.domain.user.persistence.UserMapper
import com.example.blendings_backend.domain.user.persistence.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import java.util.*

class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        CustomUserDetails(
            UserMapper.toModel(
                userRepository.findByIdOrNull(UUID.fromString(username)) ?: throw UnauthorizedException
            )
        )
}