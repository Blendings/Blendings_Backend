package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.vo.SentMailModel
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteSentMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByAuthenticationCodePort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveSentMailPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SentMailPersistenceAdapter(
    private val sentMailRepository: SentMailRepository
) : SaveSentMailPort, FindSentMailByMailAddressPort, FindSentMailByAuthenticationCodePort, DeleteSentMailByMailAddressPort {

    override fun saveSentMail(sentMailModel: SentMailModel): SentMailModel =
        SentMailMapper.toModel(
            sentMailRepository.save(SentMailMapper.toEntity(sentMailModel))
        )

    override fun findSentMailByMailAddress(mailAddress: String): SentMailModel? =
        sentMailRepository.findByIdOrNull(mailAddress)?.let {
            SentMailMapper.toModel(it)
        }

    override fun findSentMailByAuthenticationCode(authenticationCode: String): SentMailModel? =
        sentMailRepository.findByAuthenticationCode(authenticationCode)?.let {
            SentMailMapper.toModel(it)
        }

    override fun deleteSentMailByMailAddress(mailAddress: String) {
        sentMailRepository.deleteById(mailAddress)
    }
}