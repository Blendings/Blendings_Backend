package com.example.blendings_backend.domain.user.service.port.out.persistence

import com.example.blendings_backend.domain.user.service.vo.CoupleMapModel

interface SaveCoupleMapPort {

    fun saveCoupleMap(coupleMapModel: CoupleMapModel): CoupleMapModel
}