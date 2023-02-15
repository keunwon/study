package com.github.keunwon.restdocs

import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.RequestDocumentation.requestParameters
import org.springframework.restdocs.request.RequestParametersSnippet
import org.springframework.restdocs.snippet.Snippet
import org.springframework.test.web.servlet.ResultActionsDsl

fun ResultActionsDsl.makeDocument(identifier: String, init: RestDocsDsl.() -> Unit) {
    return RestDocsDsl(identifier, init).build { andDo { handle(it) } }
}

class RestDocsDsl(
    private val identifier: String,
    private val init: RestDocsDsl.() -> Unit,
) {
    private var requestHeaderSnippet: RequestHeadersSnippet? = null
    private var requestParametersSnippet: RequestParametersSnippet? = null
    private var requestFieldsSnippet: RequestFieldsSnippet? = null
    private var responseFieldsSnippet: ResponseFieldsSnippet? = null

    private val notnullSnippets: Array<Snippet>
        get() = listOfNotNull(
            requestHeaderSnippet,
            requestParametersSnippet,
            requestFieldsSnippet,
            responseFieldsSnippet,
        ).toTypedArray()

    fun requestHeaders(vararg fields: HeaderField) {
        requestHeaderSnippet = requestHeaders(fields.map { it.descriptor })
    }

    fun requestParameters(vararg fields: ParameterField) {
        requestParametersSnippet = requestParameters(fields.map { it.descriptor })
    }

    fun requestBody(vararg fields: Field) {
        requestFieldsSnippet = requestFields(fields.map { it.descriptor })
    }

    fun responseBody(vararg fields: Field) {
        responseFieldsSnippet = responseFields(fields.map { it.descriptor })
    }

    fun build(action: (RestDocumentationResultHandler) -> Unit) {
        init()
        action(document(identifier, *notnullSnippets))
    }
}
