package com.example.blendings_backend.infrastructure.error

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.NestedServletException
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Order(1)
@WebFilter
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: NestedServletException) {
            if (e.cause is GlobalException) {
                response.writeErrorCode(
                    (e.cause as GlobalException).errorCode
                )
            } else {
                response.writeErrorCode(ErrorCode.INTERNAL_SERVER_ERROR)
            }
        }
    }

    private fun HttpServletResponse.writeErrorCode(errorCode: ErrorCode): HttpServletResponse = apply {
        status = errorCode.status.value()
        contentType = MediaType.APPLICATION_JSON_VALUE
        characterEncoding = Charsets.UTF_8.displayName()
        writer.write(
            objectMapper.writeValueAsString(
                ErrorResponse(errorCode.message)
            )
        )
    }
}