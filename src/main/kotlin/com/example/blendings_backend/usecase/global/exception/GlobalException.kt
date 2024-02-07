package com.example.blendings_backend.usecase.global.exception

abstract class GlobalException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)