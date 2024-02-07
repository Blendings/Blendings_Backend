package com.example.blendings_backend.usecase.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String
) {

    // InfraStructure
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),

    // Auth
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "유효한 자격 증명이 필요합니다."),
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    INVALID_SESSION_ID(HttpStatus.UNAUTHORIZED, "Session Id가 유효하지 않습니다."),

    // Date
    MET_DAY_AFTER_THAN_CURRENT_DAY(HttpStatus.BAD_REQUEST, "만난 날은 미래일 수 없습니다."),
    MET_DAY_BEFORE_THAN_BIRTHDAY(HttpStatus.BAD_REQUEST, "만난 날은 생일보다 과거일 수 없습니다."),
    INVALID_DAY(HttpStatus.BAD_REQUEST, "무효한 날짜입니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),

    // Couple
    COUPLE_NOT_FOUND(HttpStatus.NOT_FOUND, "커플을 찾을 수 없습니다."),
    DUPLICATED_COUPLE_NICKNAME(HttpStatus.CONFLICT, "중복된 커플 별명입니다."),
    CANNOT_ACCESS_COUPLE(HttpStatus.FORBIDDEN, "해당 커플의 인원이 아닙니다."),

    // Mail
    IN_AUTHENTICATE_MAIL_ADDRESS(HttpStatus.CONFLICT, "회원가입 진행 중에 있는 메일입니다. 해킹당한 메일일 수 있습니다."),
    AUTHENTICATION_MAIL_UNSENT(HttpStatus.NOT_FOUND, "해당 메일은 인증 코드를 발송하지 않았습니다."),
    MIS_MATCH_AUTHENTICATION_CODE(HttpStatus.BAD_REQUEST, "인증 코드가 일치하지 않습니다."),
    UNAUTHENTICATED_MAIL_ADDRESS(HttpStatus.BAD_REQUEST, "인증되지 않은 메일입니다."),
    DUPLICATED_MAIL_ADDRESS(HttpStatus.CONFLICT, "중복된 메일입니다."),
    COUPLE_MAIL_ADDRESSES_CANNOT_SAME(HttpStatus.BAD_REQUEST, "메일은 같을 수 없습니다."),

    // Claim
    CLAIM_NOT_FOUND(HttpStatus.NOT_FOUND, "청구서를 찾을 수 없습니다."),
    CANNOT_ACCESS_CLAIM(HttpStatus.FORBIDDEN, "해당 청구서에 접근할 수 없습니다."),
}