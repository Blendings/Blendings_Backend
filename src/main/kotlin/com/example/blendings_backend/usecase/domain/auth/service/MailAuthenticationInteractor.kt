package com.example.blendings_backend.usecase.domain.auth.service

import com.example.blendings_backend.domain.auth.service.vo.AuthenticatedMailAddressModel
import com.example.blendings_backend.domain.auth.service.vo.SentMailModel
import com.example.blendings_backend.domain.auth.service.dto.AuthenticateMailRequest
import com.example.blendings_backend.domain.auth.service.dto.ResendMailRequest
import com.example.blendings_backend.domain.auth.service.dto.SendMailRequest
import com.example.blendings_backend.domain.auth.service.exception.AuthenticationMailUnsentException
import com.example.blendings_backend.domain.auth.service.exception.InAuthenticateMailAddressException
import com.example.blendings_backend.domain.auth.service.exception.MisMatchAuthenticationCodeException
import com.example.blendings_backend.domain.auth.service.port.`in`.AuthenticateMailAddressUseCase
import com.example.blendings_backend.domain.auth.service.port.`in`.ResendMailUseCase
import com.example.blendings_backend.domain.auth.service.port.`in`.SendAuthenticationMailUseCase
import com.example.blendings_backend.domain.auth.service.port.out.SendAuthenticationMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteSentMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveAuthenticatedMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveSentMailPort
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
        saveAuthenticatedMailPort.saveAuthenticatedMailAddress(AuthenticatedMailAddressModel(dto.mailAddress))
    }

    private fun sendMailOne(mailAddress: String) {
        findSentMailByMailAddressPort.findSentMailByMailAddress(mailAddress)?.let { throw InAuthenticateMailAddressException }
        val authenticationCode = createCode()
        sendAuthenticationMailPort.sendAuthenticationMail(mailAddress, authenticationCode)
        saveSentMailPort.saveSentMail(SentMailModel(mailAddress, authenticationCode))
    }

    private fun deletePreviouslySentMail(mail: String) {
        deleteSentMailByMailAddressPort.deleteSentMailByMailAddress(mail)
    }

    private fun verifyMatchAuthenticationCodeWithSaved(mail: String, authenticationCode: String) {
        val sentMail = findSentMailByMailAddressPort.findSentMailByMailAddress(mail) ?: throw AuthenticationMailUnsentException
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