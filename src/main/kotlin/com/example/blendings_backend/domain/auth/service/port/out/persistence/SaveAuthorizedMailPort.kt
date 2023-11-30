package com.example.blendings_backend.domain.auth.service.port.out.persistence

import com.example.blendings_backend.domain.auth.service.dao.AuthorizedMailModel

interface SaveAuthorizedMailPort {

    fun saveAuthorizedMail(authorizedMailModel: AuthorizedMailModel): AuthorizedMailModel
}