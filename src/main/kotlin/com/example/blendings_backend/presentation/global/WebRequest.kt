package com.example.blendings_backend.presentation.global

interface WebRequest<K> {
    fun toDomainRequest(): K
}