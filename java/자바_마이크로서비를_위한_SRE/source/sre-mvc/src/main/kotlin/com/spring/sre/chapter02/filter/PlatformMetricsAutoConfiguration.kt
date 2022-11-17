package com.spring.sre.chapter02.filter

import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class PlatformMetricsAutoConfiguration {
    private val log = LogManager.getLogger(PlatformMetricsAutoConfiguration::class)

    @Value("\${spring.application.name:unknown}")
    private lateinit var appName: String

    @Value("\${HOSTNAME:unkown}")
    private lateinit var host: String
}
