package com.spring.sre.chapter02.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("api")
data class WebClientHostProperties(
    val host: Map<String, String>
)
