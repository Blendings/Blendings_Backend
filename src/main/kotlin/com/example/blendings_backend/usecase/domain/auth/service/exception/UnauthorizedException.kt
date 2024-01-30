package com.example.blendings_backend.usecase.domain.auth.service.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object UnauthorizedException : GlobalException(ErrorCode.UNAUTHORIZED)