package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.dao.AuthorizedMailModel
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveAuthorizedMailPort
import org.springframework.stereotype.Component

@Component
class AuthorizedMailRepositoryAdapter(
    private val authorizedMailRepository: AuthorizedMailRepository,
    private val authorizedMailMapper: AuthorizedMailMapper
) : SaveAuthorizedMailPort {

    override fun saveAuthorizedMail(authorizedMailModel: AuthorizedMailModel): AuthorizedMailModel {
        authorizedMailRepository.save(authorizedMailMapper.toEntity(authorizedMailModel))
        return authorizedMailModel
    }
}