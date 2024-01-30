package com.example.blendings_backend.usecase.domain.user.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.UserModel

interface FindUserByMailPort {

    fun findUserByMailAddress(mailAddress: String): UserModel?
}