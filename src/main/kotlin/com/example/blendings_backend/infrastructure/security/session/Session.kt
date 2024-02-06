package com.example.blendings_backend.infrastructure.security.session

import java.io.Serializable
import java.time.LocalDateTime

interface Session : Serializable {

    fun getSessionId(): String

    fun getUserMailAddress(): String

    fun getCoupleNickname(): String

    fun getCreatedAt(): LocalDateTime

    fun getLastAccessedAt(): LocalDateTime

    fun isExistsInContext(): Boolean
}