package com.ch10.jkid

import java.lang.reflect.Type

fun serializerForBasicType(type: Type): ValueSerializer<out Any?> {
    assert(type.isPrimitiveOrString()) { "Expected primitive type or String: ${type.typeName}" }
    return serializerForType(type)!!
}

fun serializerForType(type: Type): ValueSerializer<out Any?>? =
    when (type) {
        Byte::class.java, Byte::class.javaObjectType -> ByteSerializer
        Short::class.java, Byte::class.javaObjectType -> ShortSerializer
        Int::class.java, Int::class.javaObjectType -> IntSerializer
        Long::class.java, Long::class.javaObjectType -> LongSerializer
        Float::class.java, Float::class.javaObjectType -> FloatSerializer
        Double::class.java, Double::class.javaObjectType -> DoubleSerializer
        Boolean::class.java, Boolean::class.javaObjectType -> BooleanSerializer
        String::class.java, String::class.javaObjectType -> StringSerializer
        else -> null
    }


private fun Any?.expectNumber(): Number {
    if (this !is Number) throw JKidException("Expected number, was $this")
    return this
}

object ByteSerializer : ValueSerializer<Byte> {
    override fun toJsonValue(value: Byte): Any = value
    override fun fromJsonValue(jsonValue: Any?): Byte = jsonValue.expectNumber().toByte()
}

object ShortSerializer : ValueSerializer<Short> {
    override fun toJsonValue(value: Short): Any = value
    override fun fromJsonValue(jsonValue: Any?): Short = jsonValue.expectNumber().toShort()
}

object IntSerializer : ValueSerializer<Int> {
    override fun toJsonValue(value: Int): Any = value
    override fun fromJsonValue(jsonValue: Any?): Int = jsonValue.expectNumber().toInt()
}

object LongSerializer : ValueSerializer<Long> {
    override fun toJsonValue(value: Long): Any = value
    override fun fromJsonValue(jsonValue: Any?): Long = jsonValue.expectNumber().toLong()
}

object FloatSerializer : ValueSerializer<Float> {
    override fun toJsonValue(value: Float) = value
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toFloat()
}

object DoubleSerializer : ValueSerializer<Double> {
    override fun toJsonValue(value: Double) = value
    override fun fromJsonValue(jsonValue: Any?) = jsonValue.expectNumber().toDouble()
}

object BooleanSerializer : ValueSerializer<Boolean> {
    override fun toJsonValue(value: Boolean) = value

    override fun fromJsonValue(jsonValue: Any?): Boolean {
        if (jsonValue !is Boolean) throw JKidException("Expected boolean, was: $jsonValue")
        return jsonValue
    }
}

object StringSerializer : ValueSerializer<String?> {
    override fun toJsonValue(value: String?) = value

    override fun fromJsonValue(jsonValue: Any?): String? {
        if (jsonValue !is String?) throw JKidException("Expected string, was: $jsonValue")
        return jsonValue
    }
}


class JKidException(message: String) : Exception(message)
