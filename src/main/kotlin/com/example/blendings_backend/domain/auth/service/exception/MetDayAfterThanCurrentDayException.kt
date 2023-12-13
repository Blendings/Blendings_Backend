package com.example.blendings_backend.domain.auth.service.exception

import com.example.blendings_backend.global.exception.ErrorCode
import com.example.blendings_backend.global.exception.GlobalException

object MetDayAfterThanCurrentDayException : GlobalException(ErrorCode.MET_DAY_AFTER_THAN_CURRENT_DAY)