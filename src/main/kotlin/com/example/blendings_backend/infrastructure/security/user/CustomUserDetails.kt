package com.example.blendings_backend.infrastructure.security.user

import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val user: UserJpaEntity
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf()

    override fun getPassword(): String =
        user.password

    override fun getUsername(): String =
        user.mailAddress

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}