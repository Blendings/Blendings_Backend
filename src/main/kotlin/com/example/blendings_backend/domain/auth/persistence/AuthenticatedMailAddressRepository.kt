package com.example.blendings_backend.domain.auth.persistence

import org.springframework.data.repository.CrudRepository

interface AuthenticatedMailAddressRepository : CrudRepository<AuthenticatedMailAddressEntity, String>