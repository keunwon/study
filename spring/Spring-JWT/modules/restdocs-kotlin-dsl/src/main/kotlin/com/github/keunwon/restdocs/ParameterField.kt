package com.github.keunwon.restdocs

import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName

infix fun String.paramMeans(value: String): ParameterField {
    val descriptor = parameterWithName(this)
        .description(value)
    return ParameterField(descriptor)
}

class ParameterField(val descriptor: ParameterDescriptor) {
    val option: Boolean = descriptor.isOptional

    infix fun means(value: String) = apply {
        descriptor.description(value)
    }

    infix fun isOption(value: Boolean) = apply {
        if (value) descriptor.optional()
    }
}
