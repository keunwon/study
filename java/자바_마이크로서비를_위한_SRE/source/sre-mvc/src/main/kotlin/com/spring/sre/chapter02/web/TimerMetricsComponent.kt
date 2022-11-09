package com.spring.sre.chapter02.web

import com.spring.sre.chapter02.HistogramTimerComponent
import com.spring.sre.chapter02.PercentilesTimerComponent
import com.spring.sre.chapter02.SLOTimerComponent
import com.spring.sre.chapter02.TimerComponent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chapter/2/timer")
class TimerMetricsComponent(
    private val timerComponent: TimerComponent,
    private val percentilesTimerComponent: PercentilesTimerComponent,
    private val histogramTimerComponent: HistogramTimerComponent,
    private val sloTimerComponent: SLOTimerComponent,
) {

    @GetMapping("/simple")
    fun simpleTimer(): ResponseEntity<String> {
        timerComponent.executionTime()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/percentiles")
    fun percentilesTimer(): ResponseEntity<String> {
        percentilesTimerComponent.record()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/max-percentiles")
    fun percentilesMaxTimer(): ResponseEntity<String> {
        percentilesTimerComponent.maxRecord()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/histogram")
    fun histogramTimer(): ResponseEntity<String> {
        histogramTimerComponent.record()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/max-histogram")
    fun histogramMaxTimer(): ResponseEntity<String> {
        histogramTimerComponent.maxRecord()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/slo")
    fun sloTimer(): ResponseEntity<String> {
        sloTimerComponent.record()
        return ResponseEntity.ok("ok")
    }

    @GetMapping("/max-slo")
    fun sloMaxTimer(): ResponseEntity<String> {
        sloTimerComponent.maxRecord()
        return ResponseEntity.ok("ok")
    }
}
