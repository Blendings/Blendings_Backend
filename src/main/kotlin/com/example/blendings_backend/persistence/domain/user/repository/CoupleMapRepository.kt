package com.example.blendings_backend.persistence.domain.user.repository

import com.example.blendings_backend.usecase.domain.user.vo.CoupleMapJpaEntity
import com.example.blendings_backend.usecase.domain.user.vo.UserJpaEntity
import com.example.blendings_backend.usecase.global.consts.TableName
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

interface CoupleMapRepository : Repository<CoupleMapJpaEntity, String> {

    fun save(coupleMapJpaEntity: CoupleMapJpaEntity): CoupleMapJpaEntity

    fun findById(id: String): CoupleMapJpaEntity?

    @Query(
        value = "SELECT * " +
                "FROM ${TableName.COUPLE_MAP_TABLE_NAME} c " +
                "WHERE c.male_user_id = :#{#userJpaEntity.id} OR c.female_user_id = :#{#userJpaEntity.id}",
        nativeQuery = true
    )
    fun findByUser(@Param(value = "userJpaEntity") userJpaEntity: UserJpaEntity): CoupleMapJpaEntity?
}