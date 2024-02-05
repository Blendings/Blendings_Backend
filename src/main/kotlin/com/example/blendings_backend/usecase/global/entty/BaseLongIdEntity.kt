package com.example.blendings_backend.usecase.global.entty

import javax.persistence.*

@MappedSuperclass
abstract class BaseLongIdEntity(
    id: Long?
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = id
        protected set
}