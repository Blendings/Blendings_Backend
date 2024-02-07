package com.example.blendings_backend.usecase.global.annotation

import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Transactional(isolation = Isolation.DEFAULT, readOnly = true)
@Interactor
annotation class ReadInteractor()
