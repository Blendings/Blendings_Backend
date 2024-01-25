package com.example.blendings_backend.domain.user.persistence.repository

import com.example.blendings_backend.domain.user.persistence.entity.CoupleMapJpaEntity
import com.example.blendings_backend.domain.user.persistence.entity.UserJpaEntity
import com.example.blendings_backend.global.consts.TableName
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

interface CoupleMapRepository : Repository<CoupleMapJpaEntity, String> {

    fun save(coupleMapJpaEntity: CoupleMapJpaEntity): CoupleMapJpaEntity

    fun findByNickname(nickname: String): CoupleMapJpaEntity?

    @Query(
        value = "SELECT * " +
                "FROM ${TableName.COUPLE_MAP_TABLE_NAME} c " +
                "WHERE c.male_user_id = :#{#userJpaEntity.id} OR c.female_user_id = :#{#userJpaEntity.id}",
        nativeQuery = true
    )
    fun findByUser(@Param(value = "userJpaEntity") userJpaEntity: UserJpaEntity): CoupleMapJpaEntity?
}