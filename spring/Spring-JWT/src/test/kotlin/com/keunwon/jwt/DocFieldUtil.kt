package com.keunwon.jwt

import io.restassured.module.mockmvc.response.MockMvcResponse
import org.springframework.http.HttpHeaders
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
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

data class ENUM<T: Enum<T>>(val enums: Collection<T>): DocsFieldType(JsonFieldType.STRING) {
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

operator fun MockMvcResponse.invoke(responseDsl: MockMvcResponseDsl.() -> Unit) =
    MockMvcResponseDsl(this, responseDsl).build()

class MockMvcResponseDsl(
    private val mockMvcResponse: MockMvcResponse,
    private val init: MockMvcResponseDsl.() -> Unit,
) {
    lateinit var identifier: String

    fun headers(vararg pairs: Pair<String, String>) {
        val descriptors = pairs.map { (name, means) -> headerWithName(name).description(means) }
        mockMvcResponse.then().apply(document(identifier, requestHeaders(descriptors)))
    }

    fun requestAuthorizationHeader() {
        headers(HttpHeaders.AUTHORIZATION to "로그인 성공 시 발급받은 accessToken")
    }

    fun requestBody(vararg fields: Field) {
        val descriptors = fields.map { it.descriptor }
        mockMvcResponse.then().apply(document(identifier, requestFields(descriptors)))
    }

    fun responseBody(vararg fields: Field) {
        val descriptors = fields.map { it.descriptor }
        mockMvcResponse.then().apply(document(identifier, responseFields(descriptors)))
    }

    fun build() {
        init()
    }
}
