package com.example.blendings_backend.domain.auth.service.exception

import com.example.blendings_backend.global.exception.ErrorCode
import com.example.blendings_backend.global.exception.GlobalException

object MisMatchAuthenticationCodeException : GlobalException(ErrorCode.MIS_MATCH_AUTHENTICATION_CODE)