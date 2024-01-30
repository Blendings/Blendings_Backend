package com.example.blendings_backend.usecase.domain.user.port.out.persistence

interface ExistsCoupleMapByNicknamePort {

    fun existsCoupleMapByNickname(nickname: String): Boolean
}