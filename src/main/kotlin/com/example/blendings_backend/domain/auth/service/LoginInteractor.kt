package com.example.blendings_backend.domain.auth.service

import com.example.blendings_backend.domain.auth.service.dto.LoginRequest
import com.example.blendings_backend.domain.auth.service.port.`in`.LoginUseCase
import com.example.blendings_backend.global.annotation.Interactor
import javax.servlet.http.HttpServletRequest

@Interactor
class LoginInteractor(
    private val no: String
) : LoginUseCase {

    override fun login(httpServletRequest: HttpServletRequest, loginRequest: LoginRequest) {

    }
}