package com.example.blendings_backend.usecase.domain.auth.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object MetDayAfterThanCurrentDayException : GlobalException(ErrorCode.MET_DAY_AFTER_THAN_CURRENT_DAY)