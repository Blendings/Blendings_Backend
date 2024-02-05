package com.example.blendings_backend.usecase.global.port.out.persistence

import com.example.blendings_backend.usecase.global.entty.BaseEntity

interface GetManagedEntityPort {
    fun execute(entity: BaseEntity<*>, entityClass: Class<out BaseEntity<*>>): BaseEntity<*>
}