package com.example.blendings_backend.domain.auth.presentation

import com.example.blendings_backend.domain.auth.presentation.dto.AuthDtoConverter
import com.example.blendings_backend.domain.auth.presentation.dto.request.AuthenticateMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SendMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.ResendMailRequest
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

    @PostMapping("/sign/mail/resend")
    fun mailResend(@RequestBody request: ResendMailRequest) {
        authService.resendMail(
            AuthDtoConverter.resendMailRequestToMailDto(request)
        )
    }

    @PostMapping("sign/mail/authenticate")
    fun mailAuthenticate(@RequestBody request: AuthenticateMailRequest) {
        authService.authenticateMail(
            AuthDtoConverter.authenticateMailRequestToMailCodeDto(request)
        )
    }
}