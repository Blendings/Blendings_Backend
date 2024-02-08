package com.example.blendings_backend.infrastructure.security.config

import com.example.blendings_backend.infrastructure.security.session.filter.SessionAuthenticationFilter
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityFilterChainConfig(
    private val sessionAuthenticationFilter: SessionAuthenticationFilter
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        builder.run {
            addFilterBefore(
                sessionAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )
        }
    }
}