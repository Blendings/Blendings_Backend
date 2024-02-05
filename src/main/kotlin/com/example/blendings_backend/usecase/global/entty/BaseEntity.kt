package com.example.blendings_backend.usecase.global.entty

import java.io.Serializable
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity<ID> : Serializable {
    abstract var id: ID
        protected set
}