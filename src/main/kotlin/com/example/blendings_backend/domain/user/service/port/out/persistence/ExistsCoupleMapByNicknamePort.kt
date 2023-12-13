package com.example.blendings_backend.domain.user.service.port.out.persistence

interface ExistsCoupleMapByNicknamePort {

    fun existsCoupleMapByNickname(nickname: String): Boolean
}