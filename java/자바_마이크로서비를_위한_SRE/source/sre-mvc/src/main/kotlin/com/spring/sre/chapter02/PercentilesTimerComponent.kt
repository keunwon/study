package com.spring.sre.chapter02

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@Component
class PercentilesTimerComponent {
    private val log = LogManager.getLogger(PercentilesTimerComponent::class)

    fun record() {
        getTimer().record {
            val second = ThreadLocalRandom.current().nextLong(TimedConfig.DEFAULT_MILLISECOND)
            log.info("percentiles-ms: $second")
            TimeUnit.MILLISECONDS.sleep(second)
        }
    }

    fun maxRecord() {
        getTimer().record {
            log.info("max-percentiles-ms: ${TimedConfig.MAX_MILLISECOND}")
            TimeUnit.MILLISECONDS.sleep(TimedConfig.MAX_MILLISECOND)
        }
    }

    private fun getTimer(): Timer {
        return Timer.builder("requests.test")
            .publishPercentiles(0.95, 0.85)
            .description("백분위수 타이머")
            .register(Metrics.globalRegistry)
    }
}
