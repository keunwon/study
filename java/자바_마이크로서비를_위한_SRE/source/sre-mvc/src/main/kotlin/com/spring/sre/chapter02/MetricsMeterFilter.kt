package com.spring.sre.chapter02

import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component

@Component
class MetricsMeterFilter {
    private val log = LogManager.getLogger(MetricsMeterFilter::class)

}
