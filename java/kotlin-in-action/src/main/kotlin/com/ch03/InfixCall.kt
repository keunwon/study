package com.ch03

fun printlnMap() {
    println("===== printMap =====")

    val map = mapOf(
        1 to "one",
        7 to "seven",
        53 to "fifty-three"
    )
    println(map)
    println("===== ======== =====")
}

fun main() {
    printlnMap()

    val (number, name) = 1 to "one"
    println("number: $number, name: $name")
}