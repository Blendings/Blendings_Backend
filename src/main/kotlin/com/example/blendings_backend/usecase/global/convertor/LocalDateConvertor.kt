package com.example.blendings_backend.usecase.global.convertor

import com.example.blendings_backend.usecase.global.exception.ErrorCode
import com.example.blendings_backend.usecase.global.exception.GlobalException
import java.time.DateTimeException
import java.time.LocalDate

object LocalDateConvertor {

    fun convertStringToLocalDate(stringDate: String): LocalDate {
        val dateInfo = stringDate.split(".").map { it.toInt() }
        return try {
            LocalDate.of(dateInfo[0], dateInfo[1], dateInfo[2])
        } catch (e: DateTimeException) {
            throw InvalidDayException
        }
    }

    fun convertLocalDateToString(localDate: LocalDate): String =
        localDate.year.toString() + "." + localDate.monthValue.toString() + "." + localDate.dayOfMonth.toString()
}

private object InvalidDayException : GlobalException(ErrorCode.INVALID_DAY)