package com.example.blendings_backend.presentation.domain.auth

import com.example.blendings_backend.presentation.domain.auth.dto.request.*
import com.example.blendings_backend.presentation.global.ValidationValue
import com.example.blendings_backend.presentation.global.port.out.IssueSessionPort
import com.example.blendings_backend.usecase.domain.auth.port.`in`.*
import com.example.blendings_backend.usecase.global.annotation.WebAdapter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RequestMapping("/auth")
@WebAdapter
class AuthController(
    private val sendAuthenticationMailUseCase: SendAuthenticationMailUseCase,
    private val resendMailUseCase: ResendMailUseCase,
    private val authenticateMailAddressUseCase: AuthenticateMailAddressUseCase,
    private val signUseCase: SignUseCase,
    private val loginUseCase: LoginUseCase,
    private val issueSessionPort: IssueSessionPort
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/mail")
    fun mailSend(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: SendMailWebRequest?
    ) {
        sendAuthenticationMailUseCase.sendAuthenticationMailsToCouple(request!!.toDomainRequest())
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/mail/resend")
    fun mailResend(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: ResendMailWebRequest?
    ) {
        resendMailUseCase.resendMail(request!!.toDomainRequest())
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/sign/mail/authenticate")
    fun mailAuthenticate(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: AuthenticateMailWebRequest?
    ) {
        authenticateMailAddressUseCase.authenticateMailAddress(request!!.toDomainRequest())
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign/info")
    fun sign(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        request: SignWebRequest?
    ) {
        signUseCase.sign(request!!.toDomainRequest())
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/login")
    fun login(
        @Valid
        @NotNull(message = ValidationValue.REQUEST_BODY_MESSAGE)
        @RequestBody
        loginWebRequest: LoginWebRequest?
    ) {
        val response = loginUseCase.login(loginWebRequest!!.toDomainRequest())

        issueSessionPort.execute(loginWebRequest.mailAddress, response.coupleNickname)
    }
}