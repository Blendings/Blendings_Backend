package com.example.blendings_backend.domain.user.persistence.exception

import com.example.blendings_backend.global.exception.ErrorCode
import com.example.blendings_backend.global.exception.GlobalException

object UserNotFoundException : GlobalException(ErrorCode.USER_NOT_FOUND)