package com.spring.sre.chapter03

import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@RestController
@RequestMapping("/chapter/3/zipkin")
class ZipKinApi(
    private val mvcApiWebClient: WebClient,
) {
    private val log = LogManager.getLogger(ZipKinApi::class)

    @GetMapping("/delay")
    suspend fun simple(): ResponseEntity<DelayResponse> {
        val response1 = delayMilliSecond()
        val response2 = delaySecond()
        val response3 = delayError()
        log.info("response-1: $response1, response-2: $response2, response-3: $response3")
        return ResponseEntity.ok(DelayResponse(response1, response2))
    }

    suspend fun delayMilliSecond(): String {
        return mvcApiWebClient.get()
            .uri("/chapter/2/delay/millisecond")
            .awaitExchange {
                if (!it.statusCode().is2xxSuccessful) {
                    log.info(">> api failed")
                    return@awaitExchange "failed"
                }
                it.awaitBody()
            }
    }

    suspend fun delaySecond(): String {
        return mvcApiWebClient.get()
            .uri("/chapter/2/delay/second")
            .awaitExchange {
                if (!it.statusCode().is2xxSuccessful) {
                    log.info("> second failed")
                    return@awaitExchange "failed"
                }
                it.awaitBody()
            }
    }

    suspend fun delayError(): String {
        return mvcApiWebClient.get()
            .uri("/chapter/2/delay/error")
            .awaitExchange {
                if (!it.statusCode().is2xxSuccessful) {
                    log.info("> error second")
                    return@awaitExchange "failed"
                }
                it.awaitBody()
            }
    }
}

data class DelayResponse(
    val milliSecond: String = "",
    val second: String = "",
)
