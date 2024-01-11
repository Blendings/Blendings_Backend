package com.example.blendings_backend.domain.auth.presentation

import com.example.blendings_backend.domain.auth.presentation.dto.AuthDtoConverter
import com.example.blendings_backend.domain.auth.presentation.dto.request.AuthenticateMailWebRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.ResendMailWebRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SendMailWebRequest
import com.example.blendings_backend.domain.auth.presentation.dto.request.SignWebRequest
import com.example.blendings_backend.domain.auth.service.port.`in`.*
import com.example.blendings_backend.global.annotation.WebAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RequestMapping("/auth")
@WebAdapter
class AuthController(
    private val sendAuthenticationMailUseCase: SendAuthenticationMailUseCase,
    private val resendMailUseCase: ResendMailUseCase,
    private val authenticateMailAddressUseCase: AuthenticateMailAddressUseCase,
    private val signUseCase: SignUseCase
) {

    @PostMapping("/sign/mail")
    fun mailSend(@RequestBody @Valid request: SendMailWebRequest) {
        sendAuthenticationMailUseCase.sendAuthenticationMailsToCouple(
            AuthDtoConverter.sendMailRequestToSexMailDto(request)
        )
    }

    @PostMapping("/sign/mail/resend")
    fun mailResend(@RequestBody @Valid request: ResendMailWebRequest) {
        resendMailUseCase.resendMail(
            AuthDtoConverter.resendMailRequestToMailDto(request)
        )
    }

    @PostMapping("/sign/mail/authenticate")
    fun mailAuthenticate(@RequestBody @Valid request: AuthenticateMailWebRequest) {
        authenticateMailAddressUseCase.authenticateMailAddress(
            AuthDtoConverter.authenticateMailRequestToMailCodeDto(request)
        )
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/info")
    fun sign(@RequestBody @Valid request: SignWebRequest) {
        signUseCase.sign(
            AuthDtoConverter.signRequestToSignDto(request)
        )
    }
}