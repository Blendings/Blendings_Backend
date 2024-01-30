package com.example.blendings_backend.usecase.global.exception

abstract class GlobalException(errorCode: ErrorCode) : RuntimeException(errorCode.message)