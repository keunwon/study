package com.spring.sre.chapter02

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.Metrics
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component

@Component
class CountComponent {
    private val log = LogManager.getLogger(CountComponent::class)

    private val counter = Counter.builder("bean.counter")
        .description("spring bean 개수")
        .baseUnit("beans")
        .tags("region", "seoul")
        .register(Metrics.globalRegistry)

    fun simpleIncrementCount() {
        counter.increment()
        log.info(Metrics.globalRegistry.counter("bean.counter", "region", "seoul").count())
        log.info("count: ${counter.count()}")
        log.info(counter.id.name)
    }
}
