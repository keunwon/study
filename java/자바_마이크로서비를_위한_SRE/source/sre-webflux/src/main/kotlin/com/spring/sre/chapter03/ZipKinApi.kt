package com.spring.sre.chapter03

import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("/chapter/3/zipkin")
class ZipKinApi(
    private val mvcApiWebClient: WebClient,
    private val zipkinService: ZipkinService,
) {
    private val log = LogManager.getLogger(ZipKinApi::class)

    @GetMapping("/delay")
    suspend fun simple(): ResponseEntity<DelayResponse> {
        val response1 = zipkinService.delayMilliSecond()
        val response2 = zipkinService.delaySecond()
        val response3 = zipkinService.delayError()
        log.info("response-1: $response1, response-2: $response2, response-3: $response3")
        return ResponseEntity.ok(DelayResponse(response1, response2))
    }

    @GetMapping("/delay/millisecond")
    suspend fun delayMilliSecond(): ResponseEntity<String> {
        val response = zipkinService.delayMilliSecond()
        log.info("response: $response")
        return ResponseEntity.ok(response)
    }

    @GetMapping("/delay/second")
    suspend fun delaySecond(): ResponseEntity<String> {
        val response = zipkinService.delaySecond()
        log.info("response: $response")
        return ResponseEntity.ok(response)
    }
}

data class DelayResponse(
    val milliSecond: String = "",
    val second: String = "",
)
