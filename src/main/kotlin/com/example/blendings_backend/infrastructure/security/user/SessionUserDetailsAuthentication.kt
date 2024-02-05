package com.example.blendings_backend.infrastructure.security.user

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.servlet.http.HttpSession

class SessionUserDetailsAuthentication(
    val session: HttpSession,
    val userDetails: UserDetails
) : Authentication {

    override fun getName(): String =
        userDetails.username

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        userDetails.authorities

    override fun getCredentials(): Any =
        userDetails.password

    override fun getDetails(): Any {
        TODO("Not yet implemented")
    }

    override fun getPrincipal(): Any {
        TODO("Not yet implemented")
    }

    override fun isAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }
}