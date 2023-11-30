package com.example.blendings_backend.domain.auth.persistence

import org.springframework.data.repository.CrudRepository

interface AuthorizedMailRepository : CrudRepository<AuthorizedMailEntity, String>