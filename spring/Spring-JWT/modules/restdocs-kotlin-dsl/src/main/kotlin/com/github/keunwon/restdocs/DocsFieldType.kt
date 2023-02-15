package com.github.keunwon.restdocs

import org.springframework.restdocs.payload.JsonFieldType

sealed class DocsFieldType(val jsonFieldTarget: JsonFieldType)

// https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/#documenting-your-api-request-response-payloads-fields-json-field-types
object ARRAY : DocsFieldType(JsonFieldType.ARRAY)
object BOOLEAN : DocsFieldType(JsonFieldType.BOOLEAN)
object OBJECT : DocsFieldType(JsonFieldType.OBJECT)
object NUMBER : DocsFieldType(JsonFieldType.NUMBER)
object NULL : DocsFieldType(JsonFieldType.NUMBER)
object STRING : DocsFieldType(JsonFieldType.STRING)
object ANY : DocsFieldType(JsonFieldType.VARIES)
object DATE : DocsFieldType(JsonFieldType.STRING)
object DATETIME : DocsFieldType(JsonFieldType.STRING)

infix fun String.type(docsFieldType: DocsFieldType): Field {
    val field = createField(this, docsFieldType.jsonFieldTarget)
    when (docsFieldType) {
        is DATE -> {}
        is DATETIME -> {}
        else -> {}
    }
    return field
}
