package com.example.blendings_backend.domain.auth.persistence.exception

import com.example.blendings_backend.global.exception.ErrorCode
import com.example.blendings_backend.global.exception.GlobalException

object MatchMailNotFoundException : GlobalException(ErrorCode.MATCH_MAIL_NOT_FOUND)