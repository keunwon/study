package com.spring.sre.config

import org.apache.logging.log4j.LogManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

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
            .build()
    }
}
