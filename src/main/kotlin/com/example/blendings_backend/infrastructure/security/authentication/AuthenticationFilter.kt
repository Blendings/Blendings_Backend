package com.example.blendings_backend.infrastructure.security.authentication

import com.example.blendings_backend.infrastructure.security.session.manager.HttpSessionContextManager
import com.example.blendings_backend.infrastructure.security.user.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.getSession(false) != null) {

            SecurityContextHolder.getContext().authentication = SessionUserDetailsAuthentication(
                session = request.session,
                userDetails = userDetailsService.loadUserByUsername(
                    request.session.getAttribute(HttpSessionContextManager.MAIL_ADDRESS_ATTRIBUTE_KEY).toString()
                ) as CustomUserDetails
            )
        }

        filterChain.doFilter(request, response)
    }
}