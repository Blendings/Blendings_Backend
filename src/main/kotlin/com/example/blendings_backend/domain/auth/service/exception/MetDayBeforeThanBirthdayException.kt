package com.example.blendings_backend.domain.auth.service.exception

import com.example.blendings_backend.global.exception.ErrorCode
import com.example.blendings_backend.global.exception.GlobalException

object MetDayBeforeThanBirthdayException : GlobalException(ErrorCode.MET_DAY_BEFORE_THAN_BIRTHDAY)