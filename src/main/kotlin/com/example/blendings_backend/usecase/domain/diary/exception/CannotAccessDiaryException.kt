package com.example.blendings_backend.usecase.domain.diary.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object CannotAccessDiaryException : GlobalException(ErrorCode.DIARY_NOT_FOUND)
