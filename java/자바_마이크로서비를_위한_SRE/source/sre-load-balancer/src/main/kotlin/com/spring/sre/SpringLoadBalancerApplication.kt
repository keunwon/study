package com.spring.sre

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringLoadBalancerApplication

fun main(args: Array<String>) {
    runApplication<SpringLoadBalancerApplication>(*args)
}
