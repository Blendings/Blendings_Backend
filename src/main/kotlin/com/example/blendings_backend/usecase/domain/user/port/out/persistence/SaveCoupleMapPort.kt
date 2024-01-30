package com.example.blendings_backend.usecase.domain.user.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapModel

interface SaveCoupleMapPort {

    fun saveCoupleMap(coupleMapModel: CoupleMapModel): CoupleMapModel
}