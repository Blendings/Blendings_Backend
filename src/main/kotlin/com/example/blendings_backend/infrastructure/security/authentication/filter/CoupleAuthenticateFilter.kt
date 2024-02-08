package com.example.blendings_backend.infrastructure.security.authentication.filter

import com.example.blendings_backend.infrastructure.security.authentication.SessionUserDetailsAuthentication
import com.example.blendings_backend.usecase.domain.user.exception.CannotAccessCoupleException
import org.springframework.core.annotation.Order
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.net.URLDecoder
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Order(2)
@WebFilter(urlPatterns = ["/home/*", "/claims/*", "/buckets/*", "/diaries/*", "/schedules/*"])
class CoupleAuthenticateFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authentication = SecurityContextHolder.getContext().authentication as SessionUserDetailsAuthentication

        val pathList = request.requestURI.split("/").map { URLDecoder.decode(it, Charsets.UTF_8.displayName()) }
        val coupleNickname = authentication.session.getCoupleNickname()

        if (!pathList.contains(coupleNickname)) throw CannotAccessCoupleException

        filterChain.doFilter(request, response)
    }
}