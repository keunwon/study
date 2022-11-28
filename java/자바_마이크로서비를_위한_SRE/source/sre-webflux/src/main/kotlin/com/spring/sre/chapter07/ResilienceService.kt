package com.spring.sre.chapter07

import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import java.util.concurrent.ThreadLocalRandom

@Service
class ResilienceService(
    private val mvcApiClient: WebClient,
) {
    private val log = LogManager.getLogger(ResilienceService::class)

    @Retry(name = "defaultRetry")
    suspend fun retryApi() {
        mvcApiClient.get()
            .uri("/404-api")
            .awaitExchange { it.awaitBody() }
    }

    @Retry(name = "defaultRetry")
    fun retry() {
        val rand = ThreadLocalRandom.current().nextInt(0, 101)
        log.info("default-retry: $rand")
        if (rand < 50) throw RuntimeException("error resilience-retry")
    }

    @RateLimiter(name = "defaultRateLimit")
    suspend fun rateLimit() = withContext(Dispatchers.IO) {
        val rand = ThreadLocalRandom.current().nextLong(300, 600)
        log.info("rate-limit $rand ms")
        delay(rand)
    }
}
