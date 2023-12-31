package com.example.blendings_backend.domain.auth.service.exception

import com.example.blendings_backend.global.exception.ErrorCode
import com.example.blendings_backend.global.exception.GlobalException

object AuthenticationMailUnsentException : GlobalException(ErrorCode.AUTHENTICATION_MAIL_UNSENT) {
}