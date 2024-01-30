package com.example.blendings_backend.infrastructure.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class PasswordEncoderConfig {

    @Bean
    protected fun passwordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}