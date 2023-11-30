package com.example.blendings_backend.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    // Mail
    MATCH_MAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "인증 코드와 맞는 메일을 찾을 수 없습니다."),
    IN_AUTHENTICATE_MAIL(HttpStatus.CONFLICT, "회원가입 진행 중에 있는 메일입니다. 해킹당한 메일일 수 있습니다.")
}