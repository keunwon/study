package com.spring.sre.chapter03

import brave.baggage.BaggageField
import brave.baggage.BaggagePropagationConfig
import brave.baggage.BaggagePropagationCustomizer
import com.spring.sre.chapter02.config.WebClientHostProperties
import kotlinx.coroutines.reactor.awaitSingle
import org.apache.logging.log4j.LogManager
import org.springframework.cloud.gateway.webflux.ProxyExchange
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chapter/3/proxy")
class GatewayController(
    private val hostProp: WebClientHostProperties,
) {
    private val log = LogManager.getLogger(GatewayController::class)

    @Bean
    fun baggagePropagationCustomizer(): BaggagePropagationCustomizer {
        return BaggagePropagationCustomizer {
            it.add(BaggagePropagationConfig.SingleBaggageField.remote(BaggageField.create(FAILURE_INJECTION_BAGGAGE)))
        }
    }

    @GetMapping("/path/**")
    suspend fun proxyPath(proxy: ProxyExchange<ByteArray>): ResponseEntity<ByteArray> {
        val host = hostProp.host.getValue("spring-mvc")

        BaggageField.getByName(FAILURE_INJECTION_BAGGAGE)
            .updateValue("sre-webflux")

        BaggageField.getAllValues().forEach { (key, value) ->
            log.info("key: $key, value: $value")
        }

        return proxy.uri("$host/chapter/2/delay/second").get().awaitSingle()
    }

    companion object {
        const val FAILURE_INJECTION_BAGGAGE = "failure.injection"
    }
}
