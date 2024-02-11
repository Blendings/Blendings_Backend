package com.example.blendings_backend.infrastructure.security.authentication.entrypoint

import com.example.blendings_backend.infrastructure.error.ErrorResponse
import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.run {
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = Charsets.UTF_8.displayName()
            writer.write(
                objectMapper.writeValueAsString(
                    ErrorResponse(authException.message ?: ErrorCode.UNAUTHORIZED.message)
                )
            )
        }
    }
}