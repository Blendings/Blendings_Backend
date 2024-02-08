package com.example.blendings_backend.infrastructure.security.session.filter

import com.example.blendings_backend.infrastructure.security.session.exception.InvalidSessionIdException
import com.example.blendings_backend.infrastructure.security.session.manager.SessionManager
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.time.Duration
import java.time.LocalDateTime
import javax.servlet.FilterChain
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SessionFilter(
    private val sm: SessionManager
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
        }

        filterChain.doFilter(request, response)

        sm.getCurrentSession()?.run {
            response.addCookie(
                Cookie(SessionManager.SESSION_ID_COOKIE_NAME, getSessionId()).apply {
                    path = "/"
                }
            )
        }

        sm.setCurrentSession(null)
    }

    private fun HttpServletRequest.getSessionIdCookie(): Cookie? {
        cookies.forEach { if (it.name == SessionManager.SESSION_ID_COOKIE_NAME) return it }
        return null
    }
}