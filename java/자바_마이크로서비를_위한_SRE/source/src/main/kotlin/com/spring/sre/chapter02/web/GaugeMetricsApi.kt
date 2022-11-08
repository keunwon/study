package com.spring.sre.chapter02.web

import com.spring.sre.chapter02.CityDto
import com.spring.sre.chapter02.GaugeComponent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chapter/2/gauge")
class GaugeMetricsApi(
    private val gaugeComponent: GaugeComponent,
) {

    @PostMapping("/simple-increment")
    fun simpleGaugeIncrement(): ResponseEntity<String> {
        gaugeComponent.incrementGauge()
        return ResponseEntity.ok("ok")
    }

    @PostMapping("/simple-decline")
    fun simpleGaugeDecline(): ResponseEntity<String> {
        gaugeComponent.decline()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/multi")
    fun findMultiGauge(): ResponseEntity<List<CityDto>> {
        val cityList = gaugeComponent.findMultiGauge()
        return ResponseEntity.ok(cityList)
    }
}
