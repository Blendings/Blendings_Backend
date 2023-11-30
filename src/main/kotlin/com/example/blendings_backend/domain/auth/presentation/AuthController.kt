package com.example.blendings_backend.domain.auth.presentation

import com.example.blendings_backend.domain.auth.presentation.dto.AuthDtoConverter
import com.example.blendings_backend.domain.auth.presentation.dto.request.SendMailRequest
import com.example.blendings_backend.domain.auth.service.port.`in`.AuthUseCase
import org.springframework.web.bind.annotation.*

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthUseCase
) {

    @PostMapping("/sign/mail")
    fun mailSend(@RequestBody request: SendMailRequest) {
        authService.sendMail(
            AuthDtoConverter.sendMailRequestToSexMailDto(request)
        )
    }
}