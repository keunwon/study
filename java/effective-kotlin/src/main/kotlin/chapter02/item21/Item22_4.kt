package chapter02.item21

import kotlin.properties.Delegates

var key2: String? by Delegates.vetoable(null) {property, oldValue, newValue ->
    println("$oldValue, $newValue")

    oldValue?.length ?: 0 < newValue?.length ?: 0
}

fun main() {
    key2 = null
    println("key = $key2")

    key2 = "a"
    println("key = $key2")

    key2 = "ab"
    println("key = $key2")

    key2 = "a"
    println("key = $key2")
}
