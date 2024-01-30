package com.example.blendings_backend.usecase.domain.user.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.UserModel

interface SaveUserPort {

    fun saveUser(userModel: UserModel): UserModel
}