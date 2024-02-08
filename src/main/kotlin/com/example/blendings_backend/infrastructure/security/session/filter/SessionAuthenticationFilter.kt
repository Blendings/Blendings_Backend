package com.example.blendings_backend.infrastructure.security.session.filter

import com.example.blendings_backend.infrastructure.security.authentication.SessionUserDetailsAuthentication
import com.example.blendings_backend.infrastructure.security.session.Session
import com.example.blendings_backend.infrastructure.security.session.exception.InvalidSessionIdException
import com.example.blendings_backend.infrastructure.security.session.manager.SessionManager
import com.example.blendings_backend.infrastructure.security.user.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.time.Duration
import java.time.LocalDateTime
import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SessionAuthenticationFilter(
    private val sm: SessionManager,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        request.getSessionIdCookie()?.run {
            val session = sm.getSessionById(value) ?: return@run

            if (Duration.between(session.getCreatedAt(), LocalDateTime.now()).seconds
                > SessionManager.SESSION_EXPIRE_TIME
            ) {
                sm.removeSession(session)
                throw InvalidSessionIdException
            }

            sm.setCurrentSession(session)
            SecurityContextHolder.getContext().authentication = createSessionUserDetailsAuthentication(session)
        }

        filterChain.doFilter(request, response)

        sm.getCurrentSession()?.run {
            response.addSessionIdCookie(this)
        }

        nullifyThreadLocalAuthenticate()
    }

    private fun HttpServletRequest.getSessionIdCookie(): Cookie? {
        cookies.forEach { if (it.name == SessionManager.SESSION_ID_COOKIE_NAME) return it }
        return null
    }

    private fun createSessionUserDetailsAuthentication(session: Session): SessionUserDetailsAuthentication =
        SessionUserDetailsAuthentication(
            session = session,
            userDetails = userDetailsService.loadUserByUsername(
                session.getUserMailAddress()
            ) as CustomUserDetails
        )

    private fun HttpServletResponse.addSessionIdCookie(session: Session) {
        addCookie(
            Cookie(SessionManager.SESSION_ID_COOKIE_NAME, session.getSessionId()).apply {
                path = "/"
            }
        )
    }

    private fun nullifyThreadLocalAuthenticate() {
        sm.setCurrentSession(null)
        SecurityContextHolder.clearContext()
    }
}