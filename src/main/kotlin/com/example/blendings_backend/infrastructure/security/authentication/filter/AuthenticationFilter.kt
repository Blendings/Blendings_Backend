package com.example.blendings_backend.infrastructure.security.authentication.filter

import com.example.blendings_backend.infrastructure.security.authentication.SessionUserDetailsAuthentication
import com.example.blendings_backend.infrastructure.security.session.manager.SessionManager
import com.example.blendings_backend.infrastructure.security.user.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter(
    private val sm: SessionManager,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val session = sm.getSession()

        if (session != null) {
            SecurityContextHolder.getContext().authentication = SessionUserDetailsAuthentication(
                session = session,
                userDetails = userDetailsService.loadUserByUsername(
                    session.getUserMailAddress()
                ) as CustomUserDetails
            )
        }

        filterChain.doFilter(request, response)

        SecurityContextHolder.clearContext()
    }
}