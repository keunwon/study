package com.supplement.v1

import java.io.File

fun printOnEach(list: List<Int>) {
    val result = list.onEach { println("$it") }.map { it * it }.joinToString(",")
    println(result)
}

val srcOrKotlin: Any = File("src").takeIf { it.exists() } ?: File("kotlin")

val groupingCount = (1..100).groupingBy { it % 3 }.eachCount()

fun main() {
    println("----- takeIf -----")
    val file: File = srcOrKotlin as File
    println(file.name)

    println("----- grouping -----")
    println(groupingCount)

    println("----- toMap, toMutableMap -----")
    val m1 = mapOf(1 to 2)
    val m2 = m1.toMutableMap()
    val m3 = m2.toMap()
    m2[10] = 100
    println("toMutableMap: $m2")
    println("toMap: $m3")

    println("----- maxOf, minOf -----")
    val longest = maxOf(listOf(), listOf(10), compareBy { it.size })
    val shortest = minOf(listOf(), listOf(10, 20), compareBy { it.size })
    println("maxOf: $longest")
    println("minOf: $shortest")

    println("----- list init constructor -----")
    fun initListWithConst(v: Int, size: Int) = MutableList(size) { v }
    val evens = List(10) { 2 * it }
    val thirtyZeros = initListWithConst(0, 30)
    println(initListWithConst(1, 10))
    println(evens)
    println(thirtyZeros)

    println("----- Map.getValue() -----")
    val map = mapOf(1 to "1", 2 to "2")
    println(map[1])
    try { println(map.getValue(3)) } catch (e: NoSuchElementException) { println("NoSuchElementException") }

    println("----- array compare -----")
    val arr1 = arrayOf(1, 2, 3, 4, 5)
    val arr2 = arrayOf(1, 2, 3, 4, 5)
    val arr3 = arrayOf(2, 3, 4, 5, 6)
    with (arr1) {
        println(contentToString())
        println(contentDeepToString())

        println(contentEquals(arr2))
        println(contentDeepEquals(arr2))

        println(contentEquals(arr3))
        println(contentDeepEquals(arr3))
    }
}
