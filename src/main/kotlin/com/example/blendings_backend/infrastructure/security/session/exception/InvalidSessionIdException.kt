package com.example.blendings_backend.infrastructure.security.session.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object InvalidSessionIdException : GlobalException(ErrorCode.INVALID_SESSION_ID)
