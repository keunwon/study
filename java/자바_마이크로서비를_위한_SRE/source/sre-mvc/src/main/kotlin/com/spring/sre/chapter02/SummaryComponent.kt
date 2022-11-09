package com.spring.sre.chapter02

import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.Metrics
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component
import java.util.concurrent.ThreadLocalRandom

@Component
class SummaryComponent {
    private val log = LogManager.getLogger(SummaryComponent::class)

    fun record() {
        val randNum = getRandNum()
        log.info("mb: {}", randNum)
        getSummary().record(randNum)
    }

    private fun getSummary(): DistributionSummary {
        return DistributionSummary.builder("response.size")
            .description("서머리 ")
            .baseUnit("bytes")
            .scale(100.0)
            .register(Metrics.globalRegistry)
    }

    private fun getRandNum(): Double = ThreadLocalRandom.current().nextDouble(1.0, 100.0)
}
