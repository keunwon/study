package com.keunwon.jwt

import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@ActiveProfiles("test")
@RestController
class TestHomeController {
    @GetMapping("/")
    fun home() = ResponseEntity.ok("ok")
}
