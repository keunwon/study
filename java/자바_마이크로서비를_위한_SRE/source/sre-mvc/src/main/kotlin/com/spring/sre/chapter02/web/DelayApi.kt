package com.spring.sre.chapter02.web

import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/chapter/2/delay")
class DelayApi {
    private val log = LogManager.getLogger(DelayApi::class)

    @GetMapping("/millisecond")
    fun simpleDelay(): ResponseEntity<String> {
        val randNum = ThreadLocalRandom.current().nextLong(100, 900)
        log.info("millisecond: $randNum")
        TimeUnit.MILLISECONDS.sleep(randNum)
        return ResponseEntity.ok("ms-$randNum")
    }

    @GetMapping("/second")
    fun manyDelay(): ResponseEntity<String> {
        val randNum = ThreadLocalRandom.current().nextLong(1, 3)
        log.info("second: $randNum")
        TimeUnit.SECONDS.sleep(randNum)
        return ResponseEntity.ok("second-$randNum")
    }

    @GetMapping("/error")
    fun errorResponse(): ResponseEntity<String> {
        val randNum = ThreadLocalRandom.current().nextLong(1, 3)
        log.info("error-second: $randNum")
        TimeUnit.SECONDS.sleep(randNum)
        return ResponseEntity.internalServerError().body("failed")
    }
}
