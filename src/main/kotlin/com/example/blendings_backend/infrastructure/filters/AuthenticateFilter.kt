package com.example.blendings_backend.infrastructure.filters

import com.example.blendings_backend.infrastructure.security.session.manager.HttpSessionContextManager
import com.example.blendings_backend.infrastructure.security.user.SessionUserDetailsAuthentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticateFilter(
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        SecurityContextHolder.getContext().authentication = SessionUserDetailsAuthentication(
            session = request.session,
            userDetails = userDetailsService.loadUserByUsername(
                request.session.getAttribute(HttpSessionContextManager.MAIL_ADDRESS_ATTRIBUTE_KEY).toString()
            )
        )

        filterChain.doFilter(request, response)
    }
}