package com.example.blendings_backend.usecase.domain.user.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object CoupleNotFoundException : GlobalException(ErrorCode.COUPLE_NOT_FOUND)
