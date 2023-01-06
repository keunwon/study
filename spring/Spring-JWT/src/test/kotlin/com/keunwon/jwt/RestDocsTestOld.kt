package com.keunwon.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.common.ControllerErrorHandler
import io.restassured.config.ObjectMapperConfig
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc.config
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.junit.jupiter.api.TestInstance
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.method.support.HandlerMethodArgumentResolver

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class RestDocsTestOld() {

    fun mockMvc(
        controller: Any,
        contentProvider: RestDocumentationContextProvider,
        controllerAdvices: Array<*> = arrayOf(ControllerErrorHandler()),
        argumentResolvers: Array<HandlerMethodArgumentResolver> = emptyArray(),
        httpMessageConverters: Array<HttpMessageConverter<Any>> = emptyArray(),
        objectMapper: ObjectMapper = jacksonObjectMapper(),
    ): MockMvcRequestSpecification {
        val mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setControllerAdvice(*controllerAdvices)
            .setCustomArgumentResolvers(*argumentResolvers)
            .setMessageConverters(*httpMessageConverters)
            .apply<StandaloneMockMvcBuilder>(documentationConfiguration(contentProvider))
            .build()
        return given()
            .config(
                config().objectMapperConfig(
                    ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory { _, _ -> objectMapper }
                )
            )
            .mockMvc(mockMvc)
    }
}

abstract class ApiRestDocsTestOld : RestDocsTestOld() {
    fun apiMockMvc(controller: Any, contentProvider: RestDocumentationContextProvider): MockMvcRequestSpecification {
        return mockMvc(
            controller = controller,
            contentProvider = contentProvider,
            controllerAdvices = arrayOf(ControllerErrorHandler()),
            argumentResolvers = arrayOf(),
            httpMessageConverters = httpMessageConverter(),
            objectMapper = objectMapper(),
        ).contentType(ContentType.JSON)
    }

    private fun httpMessageConverter(): Array<HttpMessageConverter<Any>> {
        return arrayOf(MappingJackson2HttpMessageConverter())
    }

    private fun objectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder()
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .featuresToDisable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS)
            .build()
    }
}
