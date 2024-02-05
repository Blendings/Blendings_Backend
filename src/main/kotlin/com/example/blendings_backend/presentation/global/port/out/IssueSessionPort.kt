package com.example.blendings_backend.presentation.global.port.out

import javax.servlet.http.HttpServletRequest

interface IssueSessionPort {
    fun execute(httpServletRequest: HttpServletRequest, userMailAddress: String, coupleNickname: String)
}