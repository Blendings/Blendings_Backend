package com.example.blendings_backend.domain.auth.service

import com.example.blendings_backend.domain.auth.service.dto.*
import com.example.blendings_backend.domain.auth.service.exception.InAuthenticateMailException
import com.example.blendings_backend.domain.auth.service.port.`in`.AuthUseCase
import com.example.blendings_backend.domain.auth.service.port.out.SendAuthenticationMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByAuthenticationCodePort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveSentMailPort
import org.springframework.stereotype.Service
import java.util.*
import kotlin.random.Random

@Service
class AuthInteractor(
    private val sendAuthenticationMailPort: SendAuthenticationMailPort,
    private val saveSentMailPort: SaveSentMailPort,
    private val findSentMailByAuthenticationCodePort: FindSentMailByAuthenticationCodePort,
    private val findSentMailByMailPort: FindSentMailByMailPort
) : AuthUseCase {

    override fun sendMail(dto: SexMailDto) {
        sendMailOne(dto.maleMail)
        sendMailOne(dto.femaleMail)
    }

    private fun sendMailOne(mail: String) {
        findSentMailByMailPort.findSentMailByMail(mail)?.let { throw InAuthenticateMailException }
        val authenticationCode = createCode()
        sendAuthenticationMailPort.sendAuthenticationMail(mail, authenticationCode)
    }

    private fun createCode(): String {
        val random = Random(Date().time)
        var code = ""
        for (i: Int in 1..6) {
            val char = random.nextInt(0, 35)
            code +=
                if (char < 10)
                    char
                else
                    (char + 55).toChar()
        }
        return code
    }
}
