package com.spring.sre.filter

import brave.baggage.BaggageField
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.metrics.web.reactive.server.DefaultWebFluxTagsProvider
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class FailureInjectionTestingHandlerFilterFunction : WebFilter {

    @Value("\${spring.application.name}")
    private lateinit var serviceName: String

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        if (serviceName == BaggageField.getByName("failure.injection").value) {
            exchange.response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            return Mono.empty()
        }
        return chain.filter(exchange)
    }
}

@Component
class FailureInjectionWebfluxTags : DefaultWebFluxTagsProvider() {

    @Value("\${spring.application.name}")
    private lateinit var serviceName: String

    override fun httpRequestTags(exchange: ServerWebExchange?, exception: Throwable?): MutableIterable<Tag> {
        return Tags.concat(
            super.httpRequestTags(exchange, exception),
            "failure.injection",
            if (serviceName == BaggageField.getByName(FAILURE_INJECTION).value) "true" else "false",
        )
    }

    companion object {
        private const val FAILURE_INJECTION = "failure.injection"
    }
}
