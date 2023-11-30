package com.example.blendings_backend.global.exception

abstract class GlobalException(errorCode: ErrorCode) : RuntimeException(errorCode.message)