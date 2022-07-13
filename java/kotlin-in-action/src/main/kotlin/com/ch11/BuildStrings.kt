package com.ch11

fun buildString(builderAction: (StringBuilder) -> Unit): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}

fun buildString2(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    sb.builderAction()
    return sb.toString()
}

fun buildString3(builderAction: StringBuilder.() -> Unit) =
    StringBuilder().apply(builderAction).toString()

val appendExcl : StringBuilder.() -> Unit = { this.append("!") }

fun StringBuilder.appendExcl2() {
    this.append("!")
}

val map = mutableMapOf(1 to "one")

fun main() {
    map.apply { this[2] = "two" }
    println(map)
    with (map) { this[3] = "three" }
    println(map)
}
