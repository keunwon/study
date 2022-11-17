package com.spring.sre.chapter02.web

import com.spring.sre.chapter02.filter.Vehicle
import org.apache.logging.log4j.LogManager
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ThreadLocalRandom

@RestController
@RequestMapping("/chapter/2/vehicle")
class VehicleMeterApi(
    private val eventPublisher: ApplicationEventPublisher,
) {
    private val log = LogManager.getLogger(VehicleMeterApi::class)

    @GetMapping("/increment")
    fun increment(): ResponseEntity<String> {
        log.info("> vehicle-increment api")
        val speed = ThreadLocalRandom.current().nextLong(1, 100)
        eventPublisher.publishEvent(Vehicle(this, speed))
        return ResponseEntity.ok("ok")
    }
}
