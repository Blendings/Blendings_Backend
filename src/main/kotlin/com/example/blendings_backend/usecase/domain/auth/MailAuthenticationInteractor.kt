package com.example.blendings_backend.usecase.domain.auth

import com.example.blendings_backend.usecase.domain.auth.dto.AuthenticateMailRequest
import com.example.blendings_backend.usecase.domain.auth.dto.ResendMailRequest
import com.example.blendings_backend.usecase.domain.auth.dto.SendMailRequest
import com.example.blendings_backend.usecase.domain.auth.exception.AuthenticationMailUnsentException
import com.example.blendings_backend.usecase.domain.auth.exception.InAuthenticateMailAddressException
import com.example.blendings_backend.usecase.domain.auth.exception.MisMatchAuthenticationCodeException
import com.example.blendings_backend.usecase.domain.auth.port.`in`.AuthenticateMailAddressUseCase
import com.example.blendings_backend.usecase.domain.auth.port.`in`.ResendMailUseCase
import com.example.blendings_backend.usecase.domain.auth.port.`in`.SendAuthenticationMailUseCase
import com.example.blendings_backend.usecase.domain.auth.port.out.SendAuthenticationMailPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.DeleteSentMailByMailAddressPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.FindSentMailByMailAddressPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.SaveAuthenticatedMailPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.SaveSentMailPort
import com.example.blendings_backend.usecase.domain.auth.vo.AuthenticatedMailAddressRedisEntity
import com.example.blendings_backend.usecase.domain.auth.vo.SentMailRedisEntity
import com.example.blendings_backend.usecase.global.annotation.Interactor
import java.util.*
import kotlin.random.Random

@Interactor
class MailAuthenticationInteractor(
    private val sendAuthenticationMailPort: SendAuthenticationMailPort,
    private val saveSentMailPort: SaveSentMailPort,
    private val findSentMailByMailAddressPort: FindSentMailByMailAddressPort,
    private val deleteSentMailByMailAddressPort: DeleteSentMailByMailAddressPort,
    private val saveAuthenticatedMailPort: SaveAuthenticatedMailPort
) : SendAuthenticationMailUseCase, ResendMailUseCase, AuthenticateMailAddressUseCase {

    override fun sendAuthenticationMailsToCouple(dto: SendMailRequest) {
        sendMailOne(dto.maleMailAddress)
        sendMailOne(dto.femaleMailAddress)
    }

    override fun resendMail(dto: ResendMailRequest) {
        deletePreviouslySentMail(dto.mailAddress)
        sendMailOne(dto.mailAddress)
    }

    override fun authenticateMailAddress(dto: AuthenticateMailRequest) {
        verifyMatchAuthenticationCodeWithSaved(dto.mailAddress, dto.authenticationCode)
        deleteSentMailByMailAddressPort.deleteSentMailByMailAddress(dto.mailAddress)
        saveAuthenticatedMailPort.saveAuthenticatedMailAddress(AuthenticatedMailAddressRedisEntity(dto.mailAddress))
    }

    private fun sendMailOne(mailAddress: String) {
        findSentMailByMailAddressPort.findSentMailByMailAddress(mailAddress)
            ?.let { throw InAuthenticateMailAddressException }
        val authenticationCode = createCode()
        sendAuthenticationMailPort.sendAuthenticationMail(mailAddress, authenticationCode)
        saveSentMailPort.saveSentMail(SentMailRedisEntity(mailAddress, authenticationCode))
    }

    private fun deletePreviouslySentMail(mail: String) {
        deleteSentMailByMailAddressPort.deleteSentMailByMailAddress(mail)
    }

    private fun verifyMatchAuthenticationCodeWithSaved(mail: String, authenticationCode: String) {
        val sentMail =
            findSentMailByMailAddressPort.findSentMailByMailAddress(mail) ?: throw AuthenticationMailUnsentException
        if (sentMail.authenticationCode != authenticationCode) throw MisMatchAuthenticationCodeException
    }

    private fun createCode(): String {
        val random = Random(Date().time)
        var code = ""
        for (i: Int in 1..6) {
            val char = random.nextInt(0, 35)
            code += if (char < 10) char
            else (char + 55).toChar()
        }
        return code
    }
}