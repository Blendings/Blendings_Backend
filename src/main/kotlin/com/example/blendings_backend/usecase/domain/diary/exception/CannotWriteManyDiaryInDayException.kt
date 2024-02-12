package com.example.blendings_backend.usecase.domain.diary.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object CannotWriteManyDiaryInDayException : GlobalException(ErrorCode.CANNOT_WRITE_MANY_DIARY_IN_DAY)
