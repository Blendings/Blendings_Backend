package com.example.blendings_backend.domain.auth.service.port.out.persistence

import com.example.blendings_backend.domain.user.service.vo.CoupleMapModel
import com.example.blendings_backend.domain.user.service.vo.UserModel

interface FindCoupleMapByUserPort {
    fun findCoupleMapByUser(userModel: UserModel): CoupleMapModel?
}