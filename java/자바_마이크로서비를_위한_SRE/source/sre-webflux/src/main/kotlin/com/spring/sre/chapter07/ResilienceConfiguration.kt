package com.spring.sre.chapter07

import io.github.resilience4j.common.bulkhead.configuration.BulkheadConfigCustomizer
import io.github.resilience4j.common.ratelimiter.configuration.RateLimiterConfigCustomizer
import io.github.resilience4j.common.retry.configuration.RetryConfigCustomizer
import io.micrometer.core.instrument.MeterRegistry
import org.apache.logging.log4j.LogManager
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@ConditionalOnBean(MeterRegistry::class)
@Configuration
class ResilienceConfiguration {
    private val log = LogManager.getLogger(ResilienceConfiguration::class)

    @Bean
    fun defaultRetryConfig(): RetryConfigCustomizer {
        return RetryConfigCustomizer.of("defaultRetry") { builder ->
            builder
                .maxAttempts(5)
                .waitDuration(Duration.ofSeconds(1))
                .intervalFunction { Duration.ofSeconds(1).toMillis() }
                .build()
        }
    }

    @Bean
    fun rateLimiterConfig(): RateLimiterConfigCustomizer {
        return RateLimiterConfigCustomizer.of("defaultRateLimit") { builder ->
            builder
                .timeoutDuration(Duration.ofSeconds(5))
                .limitRefreshPeriod(Duration.ofMillis(500))
                .limitForPeriod(50)
        }
    }

    @Bean
    fun bulkheadConfig(): BulkheadConfigCustomizer {
        return BulkheadConfigCustomizer.of("defaultBulkhead") { builder ->
            builder
                .maxConcurrentCalls(150)
                .maxWaitDuration(Duration.ofMillis(500))
        }
    }
}
