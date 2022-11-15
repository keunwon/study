package com.spring.sre.chapter02

import io.micrometer.core.annotation.Timed
import io.micrometer.core.instrument.LongTaskTimer
import io.micrometer.core.instrument.Metrics
import org.apache.logging.log4j.LogManager
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@Component
class LongTimerComponent {
    private val log = LogManager.getLogger(LongTimerComponent::class)

    @Async
    @Scheduled(fixedRate = 20 * 1000L)
    fun scrapeResources() {
        LongTaskTimer.builder("execution.long.timer")
            .description("long-timer")
            .register(Metrics.globalRegistry)
            .record(Runnable {
                val num = getRandNum()
                log.info("scrapResources-1-second: $num")
                TimeUnit.SECONDS.sleep(num)
            })
    }

    @Async
    @Timed(value = "aws.scrape", longTask = true)
    @Scheduled(fixedRate = 15 * 1000L)
    fun scrapeResources2() {
        val num = getRandNum()
        log.info("---- [s] scrapeResources-2-second: $num [s] ---")
        TimeUnit.SECONDS.sleep(num)
        log.info("---- [e] scrapeResources-2-second: $num [e] ---")
    }

    fun getRandNum(): Long = ThreadLocalRandom.current().nextLong(30, 36)
}
