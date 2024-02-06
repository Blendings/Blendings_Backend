package com.example.blendings_backend.infrastructure.filter

import com.example.blendings_backend.infrastructure.security.authentication.AuthenticationFilter
import com.example.blendings_backend.infrastructure.security.session.filter.SessionFilter
import com.example.blendings_backend.infrastructure.security.user.CustomUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Configuration
class SecurityFilterChainConfig(
    private val sessionFilter: SessionFilter,
    private val authenticationFilter: AuthenticationFilter
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.run {
            addFilterBefore(
                authenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
            addFilterBefore(
                sessionFilter,
                AuthenticationFilter::class.java
            )
        }
    }
}