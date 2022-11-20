package com.spring.sre.chapter03

import io.micrometer.core.instrument.Metrics
import io.micrometer.core.instrument.Timer
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class MyService {

    fun call() {
        val timer = Timer.resource(Metrics.globalRegistry, "calls")
            .description("calls to something")
            .publishPercentileHistogram()
            .serviceLevelObjectives(Duration.ofSeconds(1))
            .tags("service", "hi", "outcome", "success")
    }
}
