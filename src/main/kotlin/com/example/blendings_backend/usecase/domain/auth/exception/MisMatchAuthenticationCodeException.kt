package com.example.blendings_backend.usecase.domain.auth.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object MisMatchAuthenticationCodeException : GlobalException(ErrorCode.MIS_MATCH_AUTHENTICATION_CODE)