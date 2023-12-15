package com.example.blendings_backend.domain.auth.presentation

import com.example.blendings_backend.domain.auth.presentation.dto.AuthDtoConverter
import com.example.blendings_backend.domain.auth.presentation.dto.request.AuthenticateMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.ResendMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SendMailRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SignRequest
import com.example.blendings_backend.domain.auth.service.port.`in`.*
import com.example.blendings_backend.global.annotation.WebAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/auth")
@WebAdapter
class AuthController(
    private val sendMailUseCase: SendMailUseCase,
    private val resendMailUseCase: ResendMailUseCase,
    private val authenticateMailUseCase: AuthenticateMailUseCase,
    private val signUseCase: SignUseCase
) {

    @PostMapping("/sign/mail")
    fun mailSend(@RequestBody @Valid request: SendMailRequest) {
        sendMailUseCase.sendMail(
            AuthDtoConverter.sendMailRequestToSexMailDto(request)
        )
    }

    @PostMapping("/sign/mail/resend")
    fun mailResend(@RequestBody @Valid request: ResendMailRequest) {
        resendMailUseCase.resendMail(
            AuthDtoConverter.resendMailRequestToMailDto(request)
        )
    }

    @PostMapping("/sign/mail/authenticate")
    fun mailAuthenticate(@RequestBody @Valid request: AuthenticateMailRequest) {
        authenticateMailUseCase.authenticateMail(
            AuthDtoConverter.authenticateMailRequestToMailCodeDto(request)
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/info")
    fun sign(@RequestBody @Valid request: SignRequest) {
        signUseCase.sign(
            AuthDtoConverter.signRequestToSignDto(request)
        )
    }
}