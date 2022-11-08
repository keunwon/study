package com.spring.sre.config

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.Metrics
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MetricConfig {

    @Bean
    fun httpCounter(): Counter {
        return Counter.builder("requests")
            .tag("status", "200")
            .tags("method", "GET", "outcome", "SUCCESS")
            .description("http requests")
            .baseUnit("requests")
            .register(Metrics.globalRegistry)
    }
}
