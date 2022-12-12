package com.spring.sre.nginx

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime


@RestController
class HomeController {

    @Value("\${spring.application.name}")
    private lateinit var appName: String

    @GetMapping("/")
    fun home(): ResponseEntity<HomeDto> {
        return ResponseEntity.ok(createHomeDto())
    }

    @GetMapping("/no-cache")
    fun nocache(): ResponseEntity<HomeDto> {
        return ResponseEntity.ok(createHomeDto())
    }

    private fun createHomeDto(): HomeDto {
        return HomeDto(appName, LocalDateTime.now())
    }
}

data class HomeDto(
    val name: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val dateTime: LocalDateTime,
)
