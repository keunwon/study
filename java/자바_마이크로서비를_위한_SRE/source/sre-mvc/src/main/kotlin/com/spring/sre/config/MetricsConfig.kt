package com.spring.sre.config

import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.config.MeterFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class MetricsConfig {
    
    //@Bean
    fun commonTags(
        @Value("\${spring.application.name}") appName: String,
        @Value("\${spring.profiles.active}") profile: String,
    ): MeterFilter {
        return MeterFilter.commonTags(listOf(
            Tag.of("application", appName),
            Tag.of("region", "seoul"),
            Tag.of("stack", profile)
        ))
    }
}
