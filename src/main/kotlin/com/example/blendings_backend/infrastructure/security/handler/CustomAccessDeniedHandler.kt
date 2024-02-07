package com.example.blendings_backend.infrastructure.security.handler

import com.example.blendings_backend.infrastructure.error.ErrorResponse
import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.run {
            status = HttpStatus.UNAUTHORIZED.value()
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = Charsets.UTF_8.displayName()
            writer.write(
                objectMapper.writeValueAsString(
                    ErrorResponse(accessDeniedException.message ?: ErrorCode.UNAUTHORIZED.message)
                )
            )
        }
    }
}