package com.github.keunwon.restdocs

import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName

infix fun String.headerMeans(value: String): HeaderField {
    return createHeaderField(this, value)
}

fun createHeaderField(name: String, value: String, optional: Boolean = false): HeaderField {
    val description = headerWithName(name)
        .description(value)
    if (optional) description.optional()
    return HeaderField(description)
}

open class HeaderField(val descriptor: HeaderDescriptor) {
    val isIgnored: Boolean = descriptor.isOptional

    infix fun means(value: String) = apply {
        descriptor.description(value)
    }

    infix fun isIgnored(value: Boolean) = apply {
        if (value) descriptor.optional()
    }
}
