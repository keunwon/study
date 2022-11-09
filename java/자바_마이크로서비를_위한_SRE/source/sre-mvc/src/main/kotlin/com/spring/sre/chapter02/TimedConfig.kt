package com.spring.sre.chapter02

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.Metrics
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TimedConfig {

    @Bean
    fun timedAspect(): TimedAspect = TimedAspect(Metrics.globalRegistry)

    companion object {
        const val MAX_MILLISECOND = 1100L
        const val DEFAULT_MILLISECOND = 800L
    }
}
