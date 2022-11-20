package com.spring.sre.chapter03.config

import com.blueconic.browscap.UserAgentService
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags
import org.springframework.boot.actuate.metrics.web.reactive.server.DefaultWebFluxTagsProvider
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTagsProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.server.ServerWebExchange

@Configuration
class MetricsConfiguration {

    @Bean
    fun customizeRestMetrics(): WebFluxTagsProvider {
        val userAgentParser = UserAgentService().loadParser()

        return object : DefaultWebFluxTagsProvider() {
            override fun httpRequestTags(exchange: ServerWebExchange, exception: Throwable): MutableIterable<Tag> {
                val capabilities = userAgentParser.parse(exchange.request.headers.getFirst(HttpHeaders.USER_AGENT))
                return Tags.concat(
                    super.httpRequestTags(exchange, exception),
                    "browser", capabilities.browser,
                    "browser.version", capabilities.platformVersion,
                )
            }
        }
    }
}
