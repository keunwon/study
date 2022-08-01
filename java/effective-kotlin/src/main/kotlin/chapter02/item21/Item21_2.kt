package chapter02.item21

import kotlin.reflect.KProperty

var token: String? by LoggingProperty(null)
var attempts: Int by LoggingProperty(0)

private class LoggingProperty<T>(var value: T) {
    operator fun getValue(thisRef: Any?, prop: KProperty<*>): T {
        println("${prop.name} returned value $value")
        return value
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, newValue: T) {
        val name = prop.name
        println("$name changed from $value to $newValue")
        value = newValue
    }
}

fun main() {
    println(token)
    token = "token"

    println(attempts)
    attempts = 30
}
