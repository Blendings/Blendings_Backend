package com.example.blendings_backend.usecase.domain.auth.service

import com.example.blendings_backend.usecase.domain.user.exception.UserNotFoundException
import com.example.blendings_backend.usecase.domain.auth.service.dto.LoggedUserInfoResponse
import com.example.blendings_backend.usecase.domain.auth.service.dto.LoginRequest
import com.example.blendings_backend.usecase.domain.auth.service.exception.PasswordMismatchException
import com.example.blendings_backend.usecase.domain.auth.service.port.`in`.LoginUseCase
import com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence.FindCoupleMapByUserPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.FindUserByMailPort
import com.example.blendings_backend.usecase.global.annotation.Interactor
import org.springframework.security.crypto.password.PasswordEncoder

@Interactor
class LoginInteractor(
    private val passwordEncoder: PasswordEncoder,
    private val findUserByMailPort: FindUserByMailPort,
    private val findCoupleMapByUserPort: FindCoupleMapByUserPort
) : LoginUseCase {

    override fun login(loginRequest: LoginRequest): LoggedUserInfoResponse {

        val user = findUserByMailPort.findUserByMailAddress(loginRequest.mailAddress) ?: throw UserNotFoundException

        if (!passwordEncoder.matches(loginRequest.password, user.password))
            throw PasswordMismatchException

        val coupleMap = findCoupleMapByUserPort.findCoupleMapByUser(user)!!

        return LoggedUserInfoResponse(
            coupleNickname = coupleMap.nickname
        )
    }
}