package com.example.blendings_backend.global.convertor

import java.time.LocalDate

object LocalDateConvertor {

    fun convertStringToLocalDate(stringDate: String): LocalDate {
        val dateInfo = stringDate.split(".").map { it.toInt() }
        return LocalDate.of(dateInfo[0], dateInfo[1], dateInfo[2])
    }
}