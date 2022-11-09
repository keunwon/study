package com.spring.sre

import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SpringMVCApplication {
    private val log = LogManager.getLogger(SpringMVCApplication::class)

    @Bean
    fun init(
        @Value("\${spring.application.name}") appName: String,
        @Value("\${logging.config}") config: String,
    ): CommandLineRunner {
        return CommandLineRunner {
            log.info("> commandLine")
            log.info("> appName: $appName")
            log.info("> logging path: $config")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringMVCApplication>(*args)
}
