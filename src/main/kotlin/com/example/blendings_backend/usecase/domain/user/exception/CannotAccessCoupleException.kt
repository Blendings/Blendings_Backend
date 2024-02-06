package com.example.blendings_backend.usecase.domain.user.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object CannotAccessCoupleException : GlobalException(ErrorCode.CANNOT_ACCESS_COUPLE)
