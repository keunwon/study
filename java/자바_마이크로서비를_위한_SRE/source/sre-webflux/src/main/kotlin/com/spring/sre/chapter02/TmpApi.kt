package com.spring.sre.chapter02

import kotlinx.coroutines.delay
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom

@RestController
@RequestMapping("/chapter/2/tmp")
class TmpApi {
    private val log = LogManager.getLogger(TmpApi::class)

    @GetMapping("/many-delay")
    suspend fun manyDelay(): ResponseEntity<String> {
        delayApi()
        return ResponseEntity.ok("ok")
    }

    suspend fun delayApi() {
        val randNum = ThreadLocalRandom.current().nextLong(1, 3)
        log.info("many-delay-api second: $randNum")
        delay(randNum * 1000)
    }
}
