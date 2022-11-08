package com.spring.sre.chapter02

import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.LongTaskTimer
import io.micrometer.core.instrument.Metrics
import org.apache.logging.log4j.LogManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@Component
class LongTimerComponent {
    private val log = LogManager.getLogger(LongTimerComponent::class)

    @Scheduled(fixedDelay = 10000)
    fun scrapeResources() {
        LongTaskTimer.builder("execution.long.timer")
            .description("long-timer")
            .register(Metrics.globalRegistry)
            .record(Runnable {
                val num = getRandNum()
                log.info("long-timer-ms: $num")
                TimeUnit.MILLISECONDS.sleep(num)
            })
        log.info("num")
    }

    @Timed(value = "aws.scrape", longTask = true)
    @Scheduled(fixedDelay = 8000L)
    fun scrapeResources2() {
        log.info("scrapeResources")
    }

    fun getRandNum(): Long = ThreadLocalRandom.current().nextLong()
}
