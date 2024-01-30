package com.example.blendings_backend.persistence.domain.user

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object UserNotFoundException : GlobalException(ErrorCode.USER_NOT_FOUND)