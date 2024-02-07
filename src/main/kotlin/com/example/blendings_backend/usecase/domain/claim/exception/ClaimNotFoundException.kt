package com.example.blendings_backend.usecase.domain.claim.exception

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException

object ClaimNotFoundException: GlobalException(ErrorCode.CLAIM_NOT_FOUND)
