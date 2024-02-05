package com.example.blendings_backend.presentation.global

import javax.servlet.http.HttpServletResponse

object ResponseEditor {

    const val HEADER_LOCATION_NAME = "Location"

    fun HttpServletResponse.setStatusCreated(): HttpServletResponse = apply {
        status = 204
    }

    fun HttpServletResponse.setLocationHeader(
        postRequestURL: String,
        subject: Any
    ): HttpServletResponse = apply {
        setHeader(HEADER_LOCATION_NAME, "${postRequestURL}/${subject}")
    }

    fun HttpServletResponse.setLocationHeader(url: String): HttpServletResponse = apply {
        setHeader(HEADER_LOCATION_NAME, url)
    }
}