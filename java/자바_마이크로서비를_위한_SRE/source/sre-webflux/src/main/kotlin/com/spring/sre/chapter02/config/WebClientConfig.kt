package com.spring.sre.chapter02.config

import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Configuration
class WebClientConfig(
    private val hostProp: WebClientHostProperties,
) {
    private val log = LogManager.getLogger(WebClientConfig::class)

    @Bean
    fun mvcApiWebClient(): WebClient {
        val url = hostProp.host.getValue("spring-mvc")
        log.info("spring-mvc url: $url")
        return WebClient.builder()
            .baseUrl(url)
            .filters { it.add(logResponse()) }
            .build()
    }

    fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor {
            log.info("----- [s] response -----")
            log.info("status-code: ${it.statusCode()}")
            it.headers().asHttpHeaders().mapValues { (key, value) ->
                log.info("headers-$key: ${value.joinToString(", ")}")
            }
            log.info("----- [e] response -----")
            Mono.just(it)
        }
    }
}
