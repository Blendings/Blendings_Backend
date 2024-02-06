package com.example.blendings_backend.infrastructure.security.authentication

import com.example.blendings_backend.infrastructure.security.session.Session
import com.example.blendings_backend.infrastructure.security.user.CustomUserDetails
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class SessionUserDetailsAuthentication(
    val session: Session,
    val userDetails: CustomUserDetails
) : Authentication {

    override fun getName(): String =
        userDetails.username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        userDetails.authorities

    override fun getCredentials(): Any =
        userDetails.password

    override fun getDetails(): Any? = null

    override fun getPrincipal(): Any? = null

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {}
}