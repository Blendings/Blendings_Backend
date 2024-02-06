package com.example.blendings_backend.presentation.global

object ValidationValue {

    const val DATE_REGEXP = "^[0-9]{4}\\.[0-1][0-9]\\.[0-3][0-9]$"
    const val DATE_MESSAGE = "날짜는 YYYY.MM.DD 형태여야 합니다."

    const val NOT_NULL_MESSAGE = "필드는 null일 수 없습니다."
    const val NOT_BLANK_MESSAGE = "필드는 비어있을 수 없습니다."

    const val POSITIVE_MESSAGE = "필드는 1 이상의 숫자여야 합니다."
    const val POSITIVE_OR_ZERO_MESSAGE = "필드는 0 이상의 숫자여야 합니다."

    const val REQUEST_BODY_MESSAGE = "Body는 비어있을 수 없습니다."
}