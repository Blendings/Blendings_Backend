package com.example.blendings_backend.persistence.domain.auth

import com.example.blendings_backend.persistence.domain.auth.repository.SentMailRepository
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.DeleteSentMailByMailAddressPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.FindSentMailByMailAddressPort
import com.example.blendings_backend.usecase.domain.auth.port.out.persistence.SaveSentMailPort
import com.example.blendings_backend.usecase.domain.auth.vo.SentMailRedisEntity
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class SentMailPersistenceAdapter(
    private val sentMailRepository: SentMailRepository
) : SaveSentMailPort,
    FindSentMailByMailAddressPort,
    DeleteSentMailByMailAddressPort {

    override fun saveSentMail(sentMailRedisEntity: SentMailRedisEntity): SentMailRedisEntity =
        sentMailRepository.save(sentMailRedisEntity)

    override fun findSentMailByMailAddress(mailAddress: String): SentMailRedisEntity? =
        sentMailRepository.findById(mailAddress)

    override fun deleteSentMailByMailAddress(mailAddress: String) {
        sentMailRepository.deleteById(mailAddress)
    }
}