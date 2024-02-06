package com.example.blendings_backend.presentation.global.port.out

import javax.servlet.http.HttpServletRequest

interface IssueSessionPort {
    fun execute(userMailAddress: String, coupleNickname: String)
}