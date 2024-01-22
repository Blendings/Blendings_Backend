package com.example.blendings_backend.infra.session.listener

import com.example.blendings_backend.infra.session.manager.HttpSessionContextManager
import org.springframework.stereotype.Component
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

@Component
class SessionListControlHttpSessionListener(
    private val sm: HttpSessionContextManager
) : HttpSessionListener {

    override fun sessionCreated(se: HttpSessionEvent) {
        sm.addSession(se.session)
    }

    override fun sessionDestroyed(se: HttpSessionEvent) {
        sm.removeSession(se.session)
    }
}