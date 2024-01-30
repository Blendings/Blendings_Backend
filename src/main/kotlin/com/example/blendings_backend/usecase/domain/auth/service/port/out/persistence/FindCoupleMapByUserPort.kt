package com.example.blendings_backend.usecase.domain.auth.service.port.out.persistence

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapModel
import com.example.blendings_backend.usecase.domain.user.vo.UserModel

interface FindCoupleMapByUserPort {
    fun findCoupleMapByUser(userModel: UserModel): CoupleMapModel?
}