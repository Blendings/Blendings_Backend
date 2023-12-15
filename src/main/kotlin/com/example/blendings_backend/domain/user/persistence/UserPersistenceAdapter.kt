package com.example.blendings_backend.domain.user.persistence

import com.example.blendings_backend.domain.user.service.dao.UserModel
import com.example.blendings_backend.domain.user.service.port.out.persistence.ExistsUserByMailPort
import com.example.blendings_backend.domain.user.service.port.out.persistence.SaveUserPort
import com.example.blendings_backend.global.annotation.PersistenceAdapter

@PersistenceAdapter
class UserPersistenceAdapter(
    private val userRepository: UserRepository
) : SaveUserPort, ExistsUserByMailPort {

    override fun saveUser(userModel: UserModel): UserModel =
        UserMapper.toModel(
            userRepository.save(UserMapper.toEntity(userModel))
        )

    override fun existsUserByMail(mail: String): Boolean =
        userRepository.existsByMail(mail)
}