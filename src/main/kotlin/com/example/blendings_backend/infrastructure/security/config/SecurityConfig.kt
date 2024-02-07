package com.example.blendings_backend.infrastructure.security.config

import com.example.blendings_backend.infrastructure.security.handler.CustomAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val accessDeniedHandler: CustomAccessDeniedHandler,
    private val securityFilterChainConfig: SecurityFilterChainConfig,
) {

    @Bean
    protected fun config(httpSecurity: HttpSecurity): SecurityFilterChain =
        httpSecurity
            .formLogin().disable()
            .csrf().disable()
            .cors().and()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()

            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
            .and()

            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .and()

            .apply(securityFilterChainConfig)
            .and()

            .build()
}