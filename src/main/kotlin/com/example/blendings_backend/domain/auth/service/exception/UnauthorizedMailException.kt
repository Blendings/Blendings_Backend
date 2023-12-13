package com.example.blendings_backend.domain.auth.service.exception

import com.example.blendings_backend.global.exception.ErrorCode
import com.example.blendings_backend.global.exception.GlobalException

object UnauthorizedMailException : GlobalException(ErrorCode.UNAUTHORIZED_MAIL)