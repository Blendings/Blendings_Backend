package com.example.blendings_backend.infrastructure.security.session.adapter

import com.example.blendings_backend.infrastructure.security.session.SimpleSession
import com.example.blendings_backend.infrastructure.security.session.manager.SessionManager
import com.example.blendings_backend.presentation.global.port.out.IssueSessionPort
import com.example.blendings_backend.usecase.global.annotation.Adapter

@Adapter
class IssueSessionAdapter(
    private val sm: SessionManager
) : IssueSessionPort {

    override fun execute(userMailAddress: String, coupleNickname: String) {
        val session = SimpleSession(userMailAddress, coupleNickname, sm)
        sm.addSession(session)
        sm.setCurrentSession(session)
    }
}