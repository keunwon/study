package com.spring.sre.chapter02

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component

@Component
class MyComponent {
    private val log = LogManager.getLogger(MyComponent::class)

    private val timer: Timer = Timer.builder("time.something")
        .description("time some operation")
        .register(Metrics.globalRegistry)

    fun something() {
        timer.record {
            log.info("I did something")
        }
    }
}
