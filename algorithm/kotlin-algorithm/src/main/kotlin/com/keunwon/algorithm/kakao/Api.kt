package com.keunwon.algorithm.kakao

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.kotlinModule
import feign.Feign
import feign.RequestTemplate
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder

private val mapper = ObjectMapper()
    .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .registerModules(kotlinModule())

fun <T> createApi(
    url: String,
    apiType: Class<T>,
    action: ((RequestTemplate) -> Unit)? = null,
): T = Feign.builder()
    .decoder(JacksonDecoder(mapper))
    .encoder(JacksonEncoder(mapper))
    .requestInterceptor { if (action != null) action(it) }
    .target(apiType, url)
