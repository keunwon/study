package com.ch10.jkid.serialization

import com.ch10.jkid.CustomSerializer
import com.ch10.jkid.JsonExclude
import com.ch10.jkid.JsonName
import com.ch10.jkid.LocalDateSerializer
import com.ch10.jkid.ValueSerializer
import com.ch10.jkid.exercise.DateFormat
import com.ch10.jkid.joinToStringBuilder
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

fun serialize(obj: Any): String = buildString { serializeObject(obj) }

private fun StringBuilder.serializeObjectWithoutAnnotation(obj: Any) {
    val kClass = obj.javaClass.kotlin
    val properties = kClass.memberProperties

    properties.joinToStringBuilder(this, prefix = "{", postfix = "}") {
        serializeString(it.name)
        append(": ")
        serializePropertyValue(it.get(obj))
    }
}

private fun StringBuilder.serializeObject(obj: Any) {
    obj.javaClass.kotlin.memberProperties
        .filter { it.findAnnotation<JsonExclude>() == null }
        .joinToStringBuilder(this, prefix = "{", postfix = "}") {
            serializeProperty(it, obj)
        }
}

private fun StringBuilder.serializeProperty(prop: KProperty1<Any, *>, obj: Any) {
    val jsonNameAnn = prop.findAnnotation<JsonName>()
    val propName = jsonNameAnn?.name ?: prop.name
    serializeString(propName)
    append(": ")

    val value = prop.get(obj)
    val jsonValue = prop.getSerializer()?.toJsonValue(value) ?: value
    serializePropertyValue(jsonValue)
}

@Suppress("UNCHECKED_CAST")
fun KProperty<*>.getSerializer(): ValueSerializer<Any?>? {
    val valueSerializer = getCustomSerializer()
        ?: getLocalDateSerializer()
        ?: return null

    return valueSerializer as ValueSerializer<Any?>
}

private fun KProperty<*>.getCustomSerializer(): ValueSerializer<out Any?>? {
    val customSerializer = findAnnotation<CustomSerializer>() ?: return null
    val serializerClass = customSerializer.serializerClass

    return serializerClass.objectInstance ?: serializerClass.createInstance()
}

private fun KProperty<*>.getLocalDateSerializer(): ValueSerializer<out Any?>? {
    val dateFormat = findAnnotation<DateFormat>() ?: return null
    return LocalDateSerializer(dateFormat.format)
}

private fun StringBuilder.serializePropertyValue(value: Any?) {
    when (value) {
        null -> append("null")
        is String -> serializeString(value)
        is Number, is Boolean -> append(value.toString())
        is List<*> -> serializeList(value)
        else -> serializeObject(value)
    }
}

private fun StringBuilder.serializeList(data: List<*>) {
    data.joinToStringBuilder(this, prefix = "[", postfix = "]") {
        serializePropertyValue(it)
    }
}

private fun StringBuilder.serializeString(s: String) {
    append("\"")
    s.forEach { append(it.escape()) }
    append("\"")
}

private fun Char.escape(): Any =
    when (this) {
        '\\' -> "\\\\"
        '\"' -> "\\\""
        '\b' -> "\\b"
        '\u000C' -> "\\f"
        '\n' -> "\\n"
        '\r' -> "\\r"
        '\t' -> "\\t"
        else -> this
    }
