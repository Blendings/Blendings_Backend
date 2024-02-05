package com.example.blendings_backend.infrastructure.security.user

import com.example.blendings_backend.persistence.domain.user.repository.UserRepository
import com.example.blendings_backend.usecase.domain.auth.exception.UnauthorizedException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        CustomUserDetails(
            userRepository.findByMailAddress(username!!) ?: throw UnauthorizedException
        )
}