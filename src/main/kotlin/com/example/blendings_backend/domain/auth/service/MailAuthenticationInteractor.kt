package com.example.blendings_backend.domain.auth.service

import com.example.blendings_backend.domain.auth.service.dao.AuthorizedMailModel
import com.example.blendings_backend.domain.auth.service.dao.SentMailModel
import com.example.blendings_backend.domain.auth.service.dto.MailCodeDto
import com.example.blendings_backend.domain.auth.service.dto.MailDto
import com.example.blendings_backend.domain.auth.service.dto.SexMailDto
import com.example.blendings_backend.domain.auth.service.exception.AuthenticationMailUnsentException
import com.example.blendings_backend.domain.auth.service.exception.InAuthenticateMailException
import com.example.blendings_backend.domain.auth.service.exception.MisMatchAuthenticationCodeException
import com.example.blendings_backend.domain.auth.service.port.`in`.AuthenticateMailUseCase
import com.example.blendings_backend.domain.auth.service.port.`in`.ResendMailUseCase
import com.example.blendings_backend.domain.auth.service.port.`in`.SendMailUseCase
import com.example.blendings_backend.domain.auth.service.port.out.SendAuthenticationMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteSentMailByMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveAuthorizedMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveSentMailPort
import com.example.blendings_backend.global.annotation.UseCase
import java.util.*
import kotlin.random.Random

@UseCase
class MailAuthenticationInteractor(
    private val sendAuthenticationMailPort: SendAuthenticationMailPort,
    private val saveSentMailPort: SaveSentMailPort,
    private val findSentMailByMailPort: FindSentMailByMailPort,
    private val deleteSentMailByMailPort: DeleteSentMailByMailPort,
    private val saveAuthorizedMailPort: SaveAuthorizedMailPort
) : SendMailUseCase, ResendMailUseCase, AuthenticateMailUseCase {

    override fun sendMail(dto: SexMailDto) {
        sendMailOne(dto.maleMail)
        sendMailOne(dto.femaleMail)
    }

    override fun resendMail(dto: MailDto) {
        deletePreviouslySentMail(dto.mail)
        sendMailOne(dto.mail)
    }

    override fun authenticateMail(dto: MailCodeDto) {
        verifyMatchAuthenticationCodeWithSaved(dto.mail, dto.authenticationCode)
        deleteSentMailByMailPort.deleteSentMailByMail(dto.mail)
        saveAuthorizedMailPort.saveAuthorizedMail(AuthorizedMailModel(dto.mail))
    }

    private fun sendMailOne(mail: String) {
        findSentMailByMailPort.findSentMailByMail(mail)?.let { throw InAuthenticateMailException }
        val authenticationCode = createCode()
        sendAuthenticationMailPort.sendAuthenticationMail(mail, authenticationCode)
        saveSentMailPort.saveSentMail(SentMailModel(mail, authenticationCode))
    }

    private fun deletePreviouslySentMail(mail: String) {
        deleteSentMailByMailPort.deleteSentMailByMail(mail)
    }

    private fun verifyMatchAuthenticationCodeWithSaved(mail: String, authenticationCode: String) {
        val sentMail = findSentMailByMailPort.findSentMailByMail(mail) ?: throw AuthenticationMailUnsentException
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