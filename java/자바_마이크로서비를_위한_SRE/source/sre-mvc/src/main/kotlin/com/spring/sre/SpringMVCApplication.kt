package com.spring.sre

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableScheduling
@SpringBootApplication
class SpringMVCApplication

fun main(args: Array<String>) {
    runApplication<SpringMVCApplication>(*args)
}
