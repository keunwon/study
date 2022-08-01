package chapter02.item21

import kotlin.properties.Delegates

private val value by lazy { createValue() }

fun createValue(): String {
    println("createValue")
    return "lazy"
}

var items: List<String> by
        Delegates.observable(listOf()) { property, oldValue, newValue ->
            println("${property.name} $oldValue $newValue")
        }

var key: String? by
        Delegates.observable(null) { _, oldValue, newValue ->
            println("key changed from $oldValue to $newValue")
        }

fun main() {
    println(value)
    items = listOf("a", "b", "c")
    key = "key"
}
