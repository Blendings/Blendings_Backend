package com.example.blendings_backend.usecase.domain.auth.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object DuplicatedCoupleNicknameException : GlobalException(ErrorCode.DUPLICATED_COUPLE_NICKNAME)