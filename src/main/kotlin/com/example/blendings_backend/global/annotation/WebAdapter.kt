package com.example.blendings_backend.global.annotation

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

@Validated
@RestController
@Adapter
annotation class WebAdapter
/**
 * Controller에 사용
 */