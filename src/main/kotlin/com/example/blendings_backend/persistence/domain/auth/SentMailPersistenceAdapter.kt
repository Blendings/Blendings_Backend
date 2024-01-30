package com.example.blendings_backend.persistence.domain.auth

import com.example.blendings_backend.persistence.domain.auth.mapper.SentMailMapper
import com.example.blendings_backend.persistence.domain.auth.repository.SentMailRepository
import com.example.blendings_backend.domain.auth.service.port.out.persistence.DeleteSentMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.FindSentMailByMailAddressPort
import com.example.blendings_backend.domain.auth.service.port.out.persistence.SaveSentMailPort
import com.example.blendings_backend.domain.auth.service.vo.SentMailModel
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter

@PersistenceAdapter
class SentMailPersistenceAdapter(
    private val sentMailRepository: SentMailRepository
) : SaveSentMailPort,
    FindSentMailByMailAddressPort,
    DeleteSentMailByMailAddressPort {

    override fun saveSentMail(sentMailModel: SentMailModel): SentMailModel =
        SentMailMapper.toModel(
            sentMailRepository.save(SentMailMapper.toEntity(sentMailModel))
        )

    override fun findSentMailByMailAddress(mailAddress: String): SentMailModel? =
        sentMailRepository.findById(mailAddress)?.let {
            SentMailMapper.toModel(it)
        }

    override fun deleteSentMailByMailAddress(mailAddress: String) {
        sentMailRepository.deleteById(mailAddress)
    }
}