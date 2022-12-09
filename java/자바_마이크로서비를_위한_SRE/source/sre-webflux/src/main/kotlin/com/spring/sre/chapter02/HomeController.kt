package com.spring.sre.chapter02

import com.fasterxml.jackson.annotation.JsonFormat
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class HomeController {
    private val log = LogManager.getLogger(HomeController::class)

    @Value("\${spring.application.name}")
    private lateinit var appName: String

    @GetMapping("/")
    fun home(): ResponseEntity<HomeDto> {
        return ResponseEntity.ok(createHomeDto())
    }

    @GetMapping("/cache")
    fun cache(): ResponseEntity<HomeDto> {
        return ResponseEntity.ok(createHomeDto())
    }

    private fun createHomeDto(): HomeDto {
        return HomeDto(appName, LocalDateTime.now())
            .also { log.info("> response: $it") }
    }
}

data class HomeDto(
    val appName: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val dateTime: LocalDateTime,
)
