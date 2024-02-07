package com.example.blendings_backend.usecase.global.annotation

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.REPEATABLE_READ)
@Service
annotation class Interactor
/**
 * UseCase를 상속받는 Interactor(Service)에 사용
 */