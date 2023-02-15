package com.github.keunwon.restdocs

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

fun createField(
    value: String,
    type: JsonFieldType,
    optional: Boolean = false,
): Field {
    val descriptor = fieldWithPath(value)
        .attributes()
        .type(type)
        .description("")
    if (optional) descriptor.optional()
    return Field(descriptor)
}

open class Field(val descriptor: FieldDescriptor) {
    val isIgnored: Boolean = descriptor.isIgnored
    val isOptional: Boolean = descriptor.isOptional

    infix fun means(value: String): Field = apply { descriptor.description((value)) }

    infix fun isIgnored(value: Boolean): Field = apply {
        if (value) descriptor.ignored()
    }

    infix fun isOptional(value: Boolean): Field = apply {
        if (value) descriptor.optional()
    }
}
