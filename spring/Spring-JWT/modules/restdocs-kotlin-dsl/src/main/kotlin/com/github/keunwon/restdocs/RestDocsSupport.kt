package com.github.keunwon.restdocs

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.format.support.FormattingConversionService
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import java.nio.charset.StandardCharsets
import javax.servlet.Filter

@Tag("restdocs")
@ExtendWith(RestDocumentationExtension::class)
abstract class RestDocsSupport {
    private val objectMapper = jacksonObjectMapper()

    private lateinit var restDocumentation: RestDocumentationContextProvider

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        this.restDocumentation = restDocumentation
    }

    protected fun mockMvc(
        controller: Any,
        filters: MutableList<Filter> = mutableListOf(),
        resolvers: List<HandlerMethodArgumentResolver> = emptyList(),
        conversionService: FormattingConversionService? = null,
    ): MockMvc {
        return MockMvcBuilders.standaloneSetup(controller).apply {
            apply<StandaloneMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
            )
            addFilters<StandaloneMockMvcBuilder>(CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
            addFilters<StandaloneMockMvcBuilder>(*filters.toTypedArray())
            setCustomArgumentResolvers(*resolvers.toTypedArray())

            conversionService?.also { setConversionService(it) }
        }.build()
    }

    fun Any.toJson(): String = objectMapper.writeValueAsString(this)
}
