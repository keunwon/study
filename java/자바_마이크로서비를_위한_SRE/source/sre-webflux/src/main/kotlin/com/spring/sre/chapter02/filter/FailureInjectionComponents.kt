package com.spring.sre.chapter02.filter

import brave.baggage.BaggageField
import com.spring.sre.chapter03.GatewayController
import io.micrometer.core.instrument.Tag
import io.micrometer.core.instrument.Tags
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.metrics.web.reactive.server.DefaultWebFluxTagsProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

// @ConditionalOnBean(GatewayController::class)
@Component
class FailureInjectionTestingHandlerFilterFunction : WebFilter {
    private val log = LogManager.getLogger(FailureInjectionTestingHandlerFilterFunction::class)

    @Value("\${spring.application.name}")
    private lateinit var serviceName: String

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val name = BaggageField.getByName(GatewayController.FAILURE_INJECTION_BAGGAGE).value
        log.info("name: $name, serviceName: $serviceName")
        log.info("size: ${BaggageField.getAllValues().size}")
        BaggageField.getAllValues().forEach { (key, value) ->
            log.info("> key: $key, value: $value")
        }

        if (serviceName == name) {
            exchange.response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            return Mono.empty()
        }
        return chain.filter(exchange)
    }
}

@ConditionalOnBean(GatewayController::class)
@Component
class FailureInjectionWebfluxTags : DefaultWebFluxTagsProvider() {
    @Value("\${spring.application.name}")
    private lateinit var serviceName: String

    override fun httpRequestTags(exchange: ServerWebExchange?, exception: Throwable?): MutableIterable<Tag> {
        val name = BaggageField.getByName(GatewayController.FAILURE_INJECTION_BAGGAGE) ?: ""
        return Tags.concat(
            super.httpRequestTags(exchange, exception),
            "failure.injection",
            if (serviceName == name) "true" else "false",
        )
    }
}
