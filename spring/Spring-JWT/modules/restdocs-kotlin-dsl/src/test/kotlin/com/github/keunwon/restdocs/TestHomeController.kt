package com.github.keunwon.restdocs

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class TestHomeController {

    @GetMapping("/")
    fun home(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(
            mapOf(
                "id" to "아이디",
                "password" to "비밀번호",
            )
        )
    }

    @PostMapping("/json-body")
    fun jsonBody(@RequestBody jsonBody: JsonBody): ResponseEntity<JsonBody> {
        return ResponseEntity.ok(jsonBody)
    }

    @GetMapping("/query-string")
    fun queryString(
        @RequestParam username: String,
        @RequestParam password: String,
    ): ResponseEntity<Any> {
        return ResponseEntity.ok(
            mapOf(
                "username" to username,
                "password" to password,
            )
        )
    }
}

data class JsonBody(
    val code: Long,
    val username: String,
    val password: String,
)
