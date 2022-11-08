package com.spring.sre.chapter02

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@Component
class HistogramTimerComponent {
    private val log = LogManager.getLogger(HistogramTimerComponent::class)

    fun record() {
        getTimer().record {
            val second = ThreadLocalRandom.current().nextLong(TimedConfig.DEFAULT_MILLISECOND)
            log.info("histogram-ms: $second")
            TimeUnit.MILLISECONDS.sleep(second)
        }
    }

    fun maxRecord() {
        getTimer().record {
            log.info("histogram-max-ms: ${TimedConfig.MAX_MILLISECOND}")
            TimeUnit.MILLISECONDS.sleep(TimedConfig.MAX_MILLISECOND)
        }
    }

    private fun getTimer(): Timer {
        return Timer.builder("requests.histogram")
            .publishPercentileHistogram()
            .description("히스토그램 타이머")
            .register(Metrics.globalRegistry)
    }
}
