package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.persistence.domain.user.repository.UserRepository
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.ExistsUserByMailPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.FindUserByMailPort
import com.example.blendings_backend.usecase.domain.user.port.out.persistence.SaveUserPort
import com.example.blendings_backend.usecase.domain.user.vo.UserModel
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userRepository: UserRepository
) : SaveUserPort,
    FindUserByMailPort,
    ExistsUserByMailPort {

    override fun saveUser(userModel: UserModel): UserModel =
        UserMapper.toModel(
            userRepository.save(UserMapper.toEntity(userModel))
        )

    override fun findUserByMailAddress(mailAddress: String): UserModel? =
        userRepository.findByMailAddress(mailAddress)?.let { UserMapper.toModel(it) }

    override fun existsUserByMailAddress(mailAddress: String): Boolean =
        userRepository.findByMailAddress(mailAddress) != null
}