package com.example.blendings_backend.domain.auth.persistence.repository

import com.example.blendings_backend.domain.auth.persistence.entity.AuthenticatedMailAddressEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthenticatedMailAddressRepository : CrudRepository<AuthenticatedMailAddressEntity, String>