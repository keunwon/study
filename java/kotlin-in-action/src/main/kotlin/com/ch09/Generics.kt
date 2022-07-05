package com.ch09

import java.util.*

val letters = ('a'..'z').toList()

val <T> List<T>.penultimate: T
    get() = this[size - 2]

fun <T: Number> oneHalf(value: T): Double {
    return value.toDouble() / 2.0
}

fun <T: Comparable<T>> max(first: T, second: T): T {
    return if (first > second) first else second
}

fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T: Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

class Processor<T: Any> {
    fun process(value: T) {
        value.hashCode()
    }
}

fun printSum(c: Collection<*>) {
    val intList = c as? List<Int>
        ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}

fun printIntSum(c: Collection<Int>) {
    if (c is List<Int>) {
        println(c.sum())
    }
}

inline fun <reified T> isA(value: Any) = value is T

inline fun <reified T> loadService() = ServiceLoader.load(T::class.java)


fun startProjection() {
    val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val chars = mutableListOf('a', 'b', 'c')

    val unknownElements: MutableList<*> =
        if (Random().nextBoolean()) list else chars
}

fun printFirst(list: List<*>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}

fun <T> printFirst2(list: List<T>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}

fun main() {
    val nullableStringProcessor = Processor<String>()
    nullableStringProcessor.process("")

    printIntSum(listOf(1, 2, 3))

    printFirst(listOf("한글", 1234))
    printFirst2(listOf(1234, "한글"))
}
