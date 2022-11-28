package com.spring.sre.chapter07

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chapter/7")
class ResilienceApi(
    private val resilienceService: ResilienceService,
) {

    @GetMapping("/retry-api")
    suspend fun retryApi(): ResponseEntity<String> {
        resilienceService.retryApi()
        return ResponseEntity.ok("retry-ok")
    }

    @GetMapping("/retry")
    fun retry(): ResponseEntity<String> {
        resilienceService.retry()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/rate-limit")
    suspend fun rateLimit(): ResponseEntity<String> {
        resilienceService.rateLimit()
        return ResponseEntity.ok("ok")
    }
}
