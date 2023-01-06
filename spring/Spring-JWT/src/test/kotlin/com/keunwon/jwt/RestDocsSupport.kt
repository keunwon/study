package com.keunwon.jwt

import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.context.WebApplicationContext

abstract class RestDocsSupport {

    fun mockMvc(controller: Any, restDocumentation: RestDocumentationContextProvider): MockMvcRequestSpecification {
        return RestAssuredMockMvc.given()
            .mockMvc(createMockMvc(controller, restDocumentation))
            .log().all()
    }

    fun mockMvc(context: WebApplicationContext, restDocumentation: RestDocumentationContextProvider): MockMvcRequestSpecification {
        return RestAssuredMockMvc.given()
            .mockMvc(createMockMvc(context, restDocumentation))
            .log().all()
    }

    private fun createMockMvc(controller: Any, restDocumentation: RestDocumentationContextProvider): MockMvc {
        return MockMvcBuilders.standaloneSetup(controller)
            .apply<StandaloneMockMvcBuilder>(
                documentationConfiguration(restDocumentation)
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .build()
    }

    private fun createMockMvc(context: WebApplicationContext, restDocumentation: RestDocumentationContextProvider): MockMvc {
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
