package com.example.blendings_backend.domain.auth.service.port.out.persistence

import com.example.blendings_backend.domain.auth.service.vo.SentMailModel

interface SaveSentMailPort {

    fun saveSentMail(sentMailModel: SentMailModel): SentMailModel
}