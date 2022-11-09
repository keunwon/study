package com.spring.sre.chapter02.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/chapter/2/test")
class TestApi {

    @GetMapping("/requests")
    fun testApi(): ResponseEntity<String> {
        val randNum = ThreadLocalRandom.current().nextLong(100, 1000)
        TimeUnit.MILLISECONDS.sleep(randNum)
        return ResponseEntity.ok("ok")
    }
}
