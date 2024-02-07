package com.example.blendings_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class BlendingsBackendApplication

fun main(args: Array<String>) {
    runApplication<BlendingsBackendApplication>(*args)
}