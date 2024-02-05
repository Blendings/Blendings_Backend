package com.example.blendings_backend.usecase.domain.auth.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object DuplicatedMailAddressException : GlobalException(ErrorCode.DUPLICATED_MAIL_ADDRESS)