package com.github.keunwon.userauth.security

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeTestController {
    @GetMapping("/")
    fun home(): ResponseEntity<String> = ResponseEntity.ok("ok")
}
