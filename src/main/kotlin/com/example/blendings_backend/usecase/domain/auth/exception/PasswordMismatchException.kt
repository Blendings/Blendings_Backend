package com.example.blendings_backend.usecase.domain.auth.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object PasswordMismatchException : GlobalException(ErrorCode.PASSWORD_MISMATCH)