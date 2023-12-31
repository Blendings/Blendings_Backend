package com.example.blendings_backend.global.annotation

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
annotation class UseCase()
/**
 * UseCase를 상속받는 Interactor(Service)에 사용
 */