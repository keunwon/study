package com.spring.sre.chapter02.web

import com.spring.sre.chapter02.SummaryComponent
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chapter/2/summary")
class SummaryMetricsApi(
    private val summaryComponent: SummaryComponent,
) {

    @GetMapping("/record")
    fun record(): ResponseEntity<String> {
        summaryComponent.record()
        return ResponseEntity.ok("ok")
    }
}
