package com.keunwon.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.keunwon.jwt.common.ControllerErrorHandler
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.context.WebApplicationContext
import java.time.format.DateTimeFormatter
import javax.servlet.Filter

abstract class RestDocsSupport {
    fun createMockMvc(
        controller: Any,
        restDocumentation: RestDocumentationContextProvider,
        filters: List<Filter> = emptyList(),
    ): MockMvc {
        return MockMvcBuilders.standaloneSetup(controller)
            .apply<StandaloneMockMvcBuilder>(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .addFilters<StandaloneMockMvcBuilder>(*filters.toTypedArray())
            .setControllerAdvice(ControllerErrorHandler())
            .build()
    }

    fun createAuthMockMvc(
        controller: Any,
        restDocumentation: RestDocumentationContextProvider,
        filters: MutableList<Filter> = mutableListOf(),
    ): MockMvc {
        filters.add(JwtAuthorizationFilterStub)
        return createMockMvc(controller, restDocumentation, filters)
    }

    private fun createMockMvc(
        context: WebApplicationContext,
        restDocumentation: RestDocumentationContextProvider,
    ): MockMvc {
        return MockMvcBuilders.webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .build()
    }
}

val testObjectMapper: ObjectMapper = run {
    val serializer = LocalDateTimeSerializer(DateTimeFormatter.ISO_DATE_TIME)
    val javaTimeModule = JavaTimeModule().apply { addSerializer(serializer) }
    jacksonObjectMapper().registerModule(javaTimeModule)
}

fun <T> toJson(value: T): String = testObjectMapper.writeValueAsString(value)
