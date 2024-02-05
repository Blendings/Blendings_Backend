package com.example.blendings_backend.infrastructure.security.user

import com.example.blendings_backend.infrastructure.security.authentication.SessionUserDetailsAuthentication
import com.example.blendings_backend.usecase.domain.user.port.out.GetCurrentUserPort
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.annotation.Adapter
import org.springframework.security.core.context.SecurityContextHolder

@Adapter
class GetCurrentUserAdapter : GetCurrentUserPort {

    override fun execute(): UserJpaEntity {
        val authentication = SecurityContextHolder.getContext().authentication as SessionUserDetailsAuthentication
        return authentication.userDetails.user
    }
}