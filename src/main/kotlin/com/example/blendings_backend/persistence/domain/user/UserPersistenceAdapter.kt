package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.persistence.domain.user.repository.UserRepository
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.ExistsUserByMailPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.FindUserByMailPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.SaveUserPort
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userRepository: UserRepository
) : SaveUserPort,
    FindUserByMailPort,
    ExistsUserByMailPort {

    override fun saveUser(userJpaEntity: UserJpaEntity): UserJpaEntity =
        userRepository.save(userJpaEntity)

    override fun findUserByMailAddress(mailAddress: String): UserJpaEntity? =
        userRepository.findByMailAddress(mailAddress)

    override fun existsUserByMailAddress(mailAddress: String): Boolean =
        userRepository.findByMailAddress(mailAddress) != null
}