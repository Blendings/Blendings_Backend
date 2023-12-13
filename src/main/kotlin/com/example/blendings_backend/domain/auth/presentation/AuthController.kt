package com.example.blendings_backend.domain.auth.presentation

import com.example.blendings_backend.domain.auth.presentation.dto.AuthDtoConverter
import com.example.blendings_backend.domain.auth.presentation.dto.request.AuthenticateMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SendMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.ResendMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SignRequest
import com.example.blendings_backend.domain.auth.service.port.`in`.AuthUseCase
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
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

    @PostMapping("/sign/mail/authenticate")
    fun mailAuthenticate(@RequestBody request: AuthenticateMailRequest) {
        authService.authenticateMail(
            AuthDtoConverter.authenticateMailRequestToMailCodeDto(request)
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/info")
    fun sign(@RequestBody @Valid request: SignRequest) {
        authService.sign(
            AuthDtoConverter.signRequestToSignDto(request)
        )
    }
}