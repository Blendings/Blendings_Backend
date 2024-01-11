package com.example.blendings_backend.domain.user.service.port.out.persistence

import com.example.blendings_backend.domain.user.service.vo.UserModel

interface SaveUserPort {

    fun saveUser(userModel: UserModel): UserModel
}