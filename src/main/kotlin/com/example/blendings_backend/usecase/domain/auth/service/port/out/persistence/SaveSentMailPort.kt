package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

import com.example.blendings_backend.usecase.domain.auth.service.vo.SentMailModel

interface SaveSentMailPort {

    fun saveSentMail(sentMailModel: SentMailModel): SentMailModel
}