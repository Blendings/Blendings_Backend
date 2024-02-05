package com.example.blendings_backend.persistence.global

import com.example.blendings_backend.persistence.global.exception.NotDetachedEntityException
import com.example.blendings_backend.usecase.global.annotation.PersistenceAdapter
import com.example.blendings_backend.usecase.global.entty.BaseEntity
import com.example.blendings_backend.usecase.global.port.out.persistence.GetManagedEntityPort
import javax.persistence.EntityManager

@PersistenceAdapter
class GetManagedEntityPersistenceAdapter(
    private val em: EntityManager
) : GetManagedEntityPort {

    override fun execute(entity: BaseEntity<*>, entityClass: Class<out BaseEntity<*>>): BaseEntity<*> =
        (em.find(entityClass, entity.id) ?: throw NotDetachedEntityException)
}