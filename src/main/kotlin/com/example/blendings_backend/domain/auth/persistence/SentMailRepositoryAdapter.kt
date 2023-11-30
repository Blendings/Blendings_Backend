package com.example.blendings_backend.domain.auth.persistence

import com.example.blendings_backend.domain.auth.service.dao.SentMailModel
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByAuthenticationCodePort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByMailPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveSentMailPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class SentMailRepositoryAdapter(
    private val sentMailRepository: SentMailRepository,
    private val sentMailMapper: SentMailMapper
) : SaveSentMailPort, FindSentMailByMailPort, FindSentMailByAuthenticationCodePort {

    override fun saveSentMail(sentMailModel: SentMailModel): SentMailModel {
        sentMailRepository.save(sentMailMapper.toEntity(sentMailModel))
        return sentMailModel
    }

    override fun findSentMailByMail(mail: String): SentMailModel? =
        sentMailRepository.findByIdOrNull(mail)?.let {
            sentMailMapper.toModel(it)
        }

    override fun findSentMailByAuthenticationCode(authenticationCode: String): SentMailModel? =
        sentMailRepository.findByAuthenticationCode(authenticationCode)?.let {
            sentMailMapper.toModel(it)
        }
}