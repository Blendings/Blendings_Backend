package com.example.blendings_backend.global.security.user

import com.example.blendings_backend.domain.user.service.vo.UserModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    val user: UserModel
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf()

    override fun getPassword(): String =
        user.password

    override fun getUsername(): String =
        user.id.toString()

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}