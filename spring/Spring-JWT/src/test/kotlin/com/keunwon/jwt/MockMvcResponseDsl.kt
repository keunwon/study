package com.keunwon.jwt

import io.restassured.module.mockmvc.response.MockMvcResponse
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.requestParameters
import org.springframework.restdocs.request.RequestParametersSnippet
import kotlin.reflect.KClass

sealed class DocsFieldType(
    val type: JsonFieldType
)

object ARRAY : DocsFieldType(JsonFieldType.ARRAY)
object BOOLEAN : DocsFieldType(JsonFieldType.BOOLEAN)
object OBJECT : DocsFieldType(JsonFieldType.OBJECT)
object NUMBER : DocsFieldType(JsonFieldType.NUMBER)
object NULL : DocsFieldType(JsonFieldType.NULL)
object STRING : DocsFieldType(JsonFieldType.STRING)
object ANY : DocsFieldType(JsonFieldType.VARIES)
object DATE : DocsFieldType(JsonFieldType.STRING)
object DATETIME : DocsFieldType(JsonFieldType.STRING)

data class ENUM<T : Enum<T>>(val enums: Collection<T>) : DocsFieldType(JsonFieldType.STRING) {
    constructor(clazz: KClass<T>) : this(clazz.java.enumConstants.asList())
}

infix fun String.type(docsFieldType: DocsFieldType): Field {
    val field = createField(this, docsFieldType.type)
    when (docsFieldType) {
        is DATE -> {}
        is DATETIME -> {}
        else -> {}
    }
    return field
}

infix fun String.param(description: String): Parameter {
    val descriptor = parameterWithName(this)
    return Parameter(descriptor).apply { means(description) }
}

infix fun <T : Enum<T>> String.type(enumFieldType: ENUM<T>): Field {
    val field = createField(this, JsonFieldType.STRING)
    return field
}

private fun createField(value: String, type: JsonFieldType, optional: Boolean = false): Field {
    val descriptor = fieldWithPath(value)
        .type(type)
        .description("")

    if (optional) descriptor.optional()
    return Field(descriptor)
}

open class Field(val descriptor: FieldDescriptor) {
    val isIgnored: Boolean = descriptor.isIgnored
    val isOptional: Boolean = descriptor.isOptional

    var formaated: String = ""
    var default: String = ""
    var sample: String = ""

    open infix fun means(value: String): Field = apply { descriptor.description(value) }

    open fun attributes(block: Field.() -> Unit): Field = apply { block() }

    open infix fun withDefaultValue(value: String): Field = apply { default = value }

    open infix fun formattedAs(value: String): Field = apply { formaated = value }

    open infix fun example(value: String): Field = apply { sample = value }

    open infix fun isOptional(value: Boolean): Field = apply {
        if (value) descriptor.optional()
    }

    open infix fun isIgnored(value: Boolean): Field = apply {
        if (value) descriptor.ignored()
    }
}

open class Parameter(val descriptor: ParameterDescriptor) {
    open infix fun means(value: String): Parameter = apply { descriptor.description(value) }
}

fun MockMvcResponse.makeDocument(identifier: String, responseDsl: MockMvcResponseDsl.() -> Unit) =
    MockMvcResponseDsl(this, identifier, responseDsl).build()

class MockMvcResponseDsl(
    private val mockMvcResponse: MockMvcResponse,
    private val identifier: String,
    private val init: MockMvcResponseDsl.() -> Unit,
) {
    private var requestHeadersSnippet: RequestHeadersSnippet? = null
    private var requestParametersSnippet: RequestParametersSnippet? = null
    private var requestFieldsSnippet: RequestFieldsSnippet? = null
    private var responseFieldsSnippet: ResponseFieldsSnippet? = null

    fun requestHeaders(vararg pairs: Pair<String, String>) {
        val descriptors = pairs.map { (name, means) -> headerWithName(name).description(means) }
        requestHeadersSnippet = requestHeaders(descriptors)
    }

    fun requestParams(vararg fields: Parameter) {
        val descriptors = fields.map { it.descriptor }
        requestParametersSnippet = requestParameters(descriptors)
    }

    fun requestBody(vararg fields: Field) {
        val descriptions = fields.map { it.descriptor }
        requestFieldsSnippet = requestFields(descriptions)
    }

    fun responseBody(vararg fields: Field) {
        val descriptors = fields.map { it.descriptor }
        responseFieldsSnippet = responseFields(descriptors)
    }

    fun build() {
        init()
        mockMvcResponse.then().apply(
            document(
                identifier,
                *listOfNotNull(
                    requestHeadersSnippet,
                    requestParametersSnippet,
                    requestFieldsSnippet,
                    responseFieldsSnippet
                ).toTypedArray(),
            )
        )
    }
}
