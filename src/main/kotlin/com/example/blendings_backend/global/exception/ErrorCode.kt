package com.example.blendings_backend.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {
    // Mail
    MATCH_MAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "인증 코드와 맞는 메일을 찾을 수 없습니다."),
    IN_AUTHENTICATE_MAIL(HttpStatus.CONFLICT, "회원가입 진행 중에 있는 메일입니다. 해킹당한 메일일 수 있습니다."),
    AUTHENTICATION_MAIL_UNSENT(HttpStatus.NOT_FOUND, "해당 메일은 인증 코드를 발송하지 않았습니다."),
    MIS_MATCH_AUTHENTICATION_CODE(HttpStatus.BAD_REQUEST, "인증 코드가 일치하지 않습니다."),
    UNAUTHORIZED_MAIL(HttpStatus.BAD_REQUEST, "인증되지 않은 메일입니다."),
    DUPLICATED_MAIL(HttpStatus.CONFLICT, "중복된 메일입니다."),
    COUPLE_MAILS_CANNOT_SAME(HttpStatus.BAD_REQUEST, "메일은 같을 수 없습니다."),

    // Couple
    DUPLICATED_COUPLE_NICKNAME(HttpStatus.CONFLICT, "중복된 커플 별명입니다."),

    // Date
    MET_DAY_AFTER_THAN_CURRENT_DAY(HttpStatus.BAD_REQUEST, "만난 날은 미래일 수 없습니다."),
    MET_DAY_BEFORE_THAN_BIRTHDAY(HttpStatus.BAD_REQUEST, "만난 날은 생일보다 과거일 수 없습니다.")
}