package com.example.blendings_backend.persistence.global.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object NotDetachedEntityException : GlobalException(ErrorCode.INTERNAL_SERVER_ERROR)
