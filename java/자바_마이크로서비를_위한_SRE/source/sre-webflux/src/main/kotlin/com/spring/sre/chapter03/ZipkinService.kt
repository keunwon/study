package com.spring.sre.chapter03

import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@Service
class ZipkinService(
    private val mvcApiWebClient: WebClient,
) {
    private val log = LogManager.getLogger(ZipkinService::class)

    suspend fun delayMilliSecond(): String {
        return mvcApiWebClient.get()
            .uri("/chapter/2/delay/millisecond")
            .awaitExchange { it.awaitBody() }
    }

    suspend fun delaySecond(): String {
        return mvcApiWebClient.get()
            .uri("/chapter/2/delay/second")
            .awaitExchange { it.awaitBody() }
    }

    suspend fun delayError(): String {
        return mvcApiWebClient.get()
            .uri("/chapter/2/delay/error")
            .awaitExchange { it.awaitBody() }
    }
}
