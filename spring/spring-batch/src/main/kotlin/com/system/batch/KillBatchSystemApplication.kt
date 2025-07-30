package com.system.batch

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import kotlin.system.exitProcess


@SpringBootApplication
class KillBatchSystemApplication

fun main(args: Array<String>) {
    exitProcess(SpringApplication.exit(runApplication<KillBatchSystemApplication>(*args)))
}
