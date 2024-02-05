package com.example.blendings_backend.usecase.domain.auth

import com.example.blendings_backend.usecase.domain.user.exception.UserNotFoundException
import com.example.blendings_backend.usecase.domain.auth.dto.LoggedUserInfoResponse
import com.example.blendings_backend.usecase.domain.auth.dto.LoginRequest
import com.example.blendings_backend.usecase.domain.auth.exception.PasswordMismatchException
import com.example.blendings_backend.usecase.domain.auth.port.`in`.LoginUseCase
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.FindCoupleMapByUserPort
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
            coupleNickname = coupleMap.id!!
        )
    }
}