package com.example.blendings_backend.usecase.domain.auth.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object CoupleMailAddressesCannotSameException : GlobalException(ErrorCode.COUPLE_MAIL_ADDRESSES_CANNOT_SAME)