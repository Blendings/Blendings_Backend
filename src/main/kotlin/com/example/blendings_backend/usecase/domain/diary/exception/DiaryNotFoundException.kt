package com.example.blendings_backend.usecase.domain.diary.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object DiaryNotFoundException : GlobalException(ErrorCode.DIARY_NOT_FOUND)
