package com.spring.sre.chapter02

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@Component
class SLOTimerComponent {
    private val log = LogManager.getLogger(SLOTimerComponent::class)

    fun record() {
        getTimer().record {
            val second = ThreadLocalRandom.current().nextLong(TimedConfig.DEFAULT_MILLISECOND)
            log.info("SLO-ms: $second")
            TimeUnit.MILLISECONDS.sleep(second)
        }
    }

    fun maxRecord() {
        getTimer().record {
            log.info("SLO-max-ms: ${TimedConfig.MAX_MILLISECOND}")
            TimeUnit.MILLISECONDS.sleep(TimedConfig.MAX_MILLISECOND)
        }
    }

    private fun getTimer(): Timer {
        return Timer.builder("requests.slo")
            .publishPercentiles(0.9, 0.99)
            .serviceLevelObjectives(Duration.ofMillis(200), Duration.ofSeconds(1))
            .register(Metrics.globalRegistry)
    }
}
