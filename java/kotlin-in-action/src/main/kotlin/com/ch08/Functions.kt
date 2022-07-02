package com.ch08

val sum = { x: Int, y: Int -> x + y}

val action = { println(42) }

var canReturnNull: (Int, Int) -> Int? = { x, y -> null }

var funOrNull: ((Int, Int) -> Int)? = null

fun performRequest(url: String, callback: (code: Int, content: String) -> Unit) {
    println(url)
    callback(404, "404 not found")
}

fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("The result is $result")
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()

    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}

fun <T> Collection<T>.joinToString(
    separator: String = "",
    prefix: String = "",
    postfix: String = "",
    transform: (T) -> String = { it.toString() }
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (0 < index) result.append(separator)
        result.append(transform(element))
    }
    result.append(postfix)

    return result.toString()
}

fun main() {
    val url = "http://kot1.in"
    performRequest(url) { code, content -> println("code: $code, content: $content") }
}
