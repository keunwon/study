package com.spring.sre.chapter03.config

import com.blueconic.browscap.UserAgentService
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags
import org.springframework.boot.actuate.metrics.web.reactive.client.DefaultWebClientExchangeTagsProvider
import org.springframework.boot.actuate.metrics.web.reactive.client.WebClientExchangeTagsProvider
import org.springframework.boot.actuate.metrics.web.reactive.server.DefaultWebFluxTagsProvider
import org.springframework.boot.actuate.metrics.web.reactive.server.WebFluxTagsProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.server.ServerWebExchange

@Configuration
class MetricsConfiguration {

    @Primary
    @Bean
    fun customizeRestMetrics(): WebFluxTagsProvider {
        val userAgentParser = UserAgentService().loadParser()

        return object : DefaultWebFluxTagsProvider() {
            override fun httpRequestTags(exchange: ServerWebExchange, exception: Throwable?): MutableIterable<Tag> {
                val capabilities = userAgentParser.parse(exchange.request.headers.getFirst(HttpHeaders.USER_AGENT))
                return Tags.concat(
                    super.httpRequestTags(exchange, exception),
                    "browser", capabilities.browser,
                    "browser.version", capabilities.platformVersion,
                )
            }
        }
    }

    @Bean
    fun webClientExchangeTagsProvider(): WebClientExchangeTagsProvider {
        return object : DefaultWebClientExchangeTagsProvider() {
            override fun tags(
                request: ClientRequest?,
                response: ClientResponse?,
                throwable: Throwable?
            ): MutableIterable<Tag> {
                val subscription = response
                    ?.headers()?.asHttpHeaders()
                    ?.getFirst("subscription")
                    ?: "basic"

                return Tags.concat(
                    super.tags(request, response, throwable),
                    "subscription.leve", subscription,
                )
            }
        }
    }
}
