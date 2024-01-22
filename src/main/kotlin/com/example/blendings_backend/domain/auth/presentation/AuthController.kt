package com.example.blendings_backend.domain.auth.presentation

import com.example.blendings_backend.domain.auth.presentation.dto.request.*
import com.example.blendings_backend.domain.auth.service.dto.LoggedUserInfoResponse
import com.example.blendings_backend.domain.auth.service.port.`in`.*
import com.example.blendings_backend.global.annotation.WebAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RequestMapping("/auth")
@WebAdapter
class AuthController(
    private val sendAuthenticationMailUseCase: SendAuthenticationMailUseCase,
    private val resendMailUseCase: ResendMailUseCase,
    private val authenticateMailAddressUseCase: AuthenticateMailAddressUseCase,
    private val signUseCase: SignUseCase,
    private val loginUseCase: LoginUseCase
) {

    @PostMapping("/sign/mail")
    fun mailSend(@RequestBody @Valid request: SendMailWebRequest) {
        sendAuthenticationMailUseCase.sendAuthenticationMailsToCouple(request.toDomainRequest())
    }

    @PostMapping("/sign/mail/resend")
    fun mailResend(@RequestBody @Valid request: ResendMailWebRequest) {
        resendMailUseCase.resendMail(request.toDomainRequest())
    }

    @PostMapping("/sign/mail/authenticate")
    fun mailAuthenticate(@RequestBody @Valid request: AuthenticateMailWebRequest) {
        authenticateMailAddressUseCase.authenticateMailAddress(request.toDomainRequest())
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/info")
    fun sign(@RequestBody @Valid request: SignWebRequest) {
        signUseCase.sign(request.toDomainRequest())
    }

    @GetMapping("/login")
    fun login(
        httpServletRequest: HttpServletRequest,
        @RequestBody @Valid loginWebRequest: LoginWebRequest
    ): LoggedUserInfoResponse =
        loginUseCase.login(httpServletRequest, loginWebRequest.toDomainRequest())
}