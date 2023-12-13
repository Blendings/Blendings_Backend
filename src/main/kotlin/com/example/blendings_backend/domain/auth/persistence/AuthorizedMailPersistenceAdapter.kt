package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.dao.AuthorizedMailModel
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteAuthorizedMailByMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.ExistsAuthorizedMailByMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveAuthorizedMailPort
import org.springframework.stereotype.Component

@Component
class AuthorizedMailPersistenceAdapter(
    private val authorizedMailRepository: AuthorizedMailRepository,
    private val authorizedMailMapper: AuthorizedMailMapper
) : SaveAuthorizedMailPort, ExistsAuthorizedMailByMailPort, DeleteAuthorizedMailByMailPort {

    override fun saveAuthorizedMail(authorizedMailModel: AuthorizedMailModel): AuthorizedMailModel {
        authorizedMailRepository.save(authorizedMailMapper.toEntity(authorizedMailModel))
        return authorizedMailModel
    }

    override fun existsAuthorizedMailByMail(mail: String): Boolean =
        authorizedMailRepository.existsById(mail)

    override fun deleteAuthorizedMailBuMail(mail: String) {
        authorizedMailRepository.deleteById(mail)
    }
}