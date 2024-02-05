package com.example.blendings_backend.infrastructure.security.session.listener

import com.example.blendings_backend.infrastructure.security.session.IssueSessionAdapter
import com.example.blendings_backend.infrastructure.security.session.manager.HttpSessionContextManager
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

@Component
class SessionListControlHttpSessionListener(
    private val sm: HttpSessionContextManager
) : HttpSessionListener {

    override fun sessionCreated(se: HttpSessionEvent) {
        /**
         * 흐름상 이 위치에서 동작하는 것이 맞으나,
         * 세션 생성 이후 attribute 설정이 필요해 아래 로직에서 따로 호출
         * @see IssueSessionAdapter.execute
         */
        // sm.addSession(se.session)
    }

    override fun sessionDestroyed(se: HttpSessionEvent) {
        sm.removeSession(se.session)
    }
}