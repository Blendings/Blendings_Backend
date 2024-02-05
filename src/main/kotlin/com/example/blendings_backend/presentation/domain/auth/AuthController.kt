package com.example.blendings_backend.presentation.domain.auth

import com.example.blendings_backend.presentation.domain.auth.dto.request.*
import com.example.blendings_backend.usecase.domain.auth.port.`in`.*
import com.example.blendings_backend.usecase.global.annotation.WebAdapter
import com.example.blendings_backend.usecase.global.consts.ValidationValue
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import javax.validation.constraints.NotNull

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
    fun mailSend(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: SendMailWebRequest
    ) {
        sendAuthenticationMailUseCase.sendAuthenticationMailsToCouple(request.toDomainRequest())
    }

    @PostMapping("/sign/mail/resend")
    fun mailResend(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: ResendMailWebRequest
    ) {
        resendMailUseCase.resendMail(request.toDomainRequest())
    }

    @PostMapping("/sign/mail/authenticate")
    fun mailAuthenticate(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: AuthenticateMailWebRequest
    ) {
        authenticateMailAddressUseCase.authenticateMailAddress(request.toDomainRequest())
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/info")
    fun sign(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: SignWebRequest
    ) {
        signUseCase.sign(request.toDomainRequest())
    }

    @GetMapping("/login")
    fun login(
        httpServletRequest: HttpServletRequest,
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        loginWebRequest: LoginWebRequest
    ) {
        val response = loginUseCase.login(loginWebRequest.toDomainRequest())

        httpServletRequest.session.invalidate()
        val session = httpServletRequest.session
        session.run {
            setAttribute("mailAddress", loginWebRequest.mailAddress)
            setAttribute("coupleNickname", response.coupleNickname)
        }
    }
}