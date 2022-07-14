package com.supplement.v2

fun <T> List<T>.mychunked(size: Int) = windowed(size, size)

fun <T, R> List<T>.myZipWithNext(f: (T, T) -> R) =
    windowed(size = 2, step = 1)
        .filter { it.size == 2 }
        .map { (x, y) -> f(x, y) }

fun main() {
    val l = listOf(1, 2, 3, 4, 5, 6, 7)
    println("l = ${1}")

    println("l.chunked(size=3)")
    l.chunked(3).forEach { println(it) }

    println("l.windowed(size=3,step=1)")
    l.windowed(size = 3, step = 1).forEach { println(it) }


    println("l.zipWithNext")
    l.zipWithNext { x, y -> println("{$x, $y}") }

    println("l.mychunked(size=3)")
    l.mychunked(size = 3).forEach { println(it) }

    println("l.myZipWithNext")
    l.myZipWithNext { x, y -> println("$x, $y") }

    println("----- shuffle -----")
    val items = (1..5).toMutableList()
    items.shuffle()
    println(items)

    println("----- shuffled -----")
    val readonlyItems = (1..5).toList()
    println(readonlyItems.shuffled())

    println("----- replaceAll -----")
    items.replaceAll { it * 2 }
    println(items)

    println("----- fill -----")
    items.fill(5)
    println(items)
}
