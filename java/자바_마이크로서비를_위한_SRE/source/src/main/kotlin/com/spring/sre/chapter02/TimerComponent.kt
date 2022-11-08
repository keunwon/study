package com.spring.sre.chapter02

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class TimerComponent {
    private val log = LogManager.getLogger(TimerComponent::class)

    private val timer = Timer.builder("execution.time")
        .description("a description of what this timer does")
        .distributionStatisticExpiry(Duration.ofSeconds(10))
        .distributionStatisticBufferLength(3)
        .serviceLevelObjectives(Duration.ofMillis(100), Duration.ofSeconds(1))
        .publishPercentileHistogram()
        .tag("region", "seoul")
        .register(Metrics.globalRegistry)

    fun executionTime() {
        timer.record {
            log.info("timer.record")
        }
        log.info("${timer.count()}")
    }
}
