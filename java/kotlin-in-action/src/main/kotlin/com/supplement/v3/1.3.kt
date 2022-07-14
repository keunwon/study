package com.supplement.v3

import kotlin.random.Random

fun foo(s: String?) {
    require(s is String)
    println(s)
}

fun getBody() = when (val n = getNum()) {
    3 -> println(n + 1)
    4 -> println(n + 1)
    5 -> println(n + 1)
    else -> throw IllegalArgumentException("")
}

fun getNum(): Int {
    return 3
}

annotation class Foo {
    enum class Direction { UP, DOWN, LEFT, RIGHT }

    annotation class Bar

    companion object {
        fun foo(): Int = 42
        val bar: Int = 42
    }
}

inline class Name(val s: String)

val number = Random.nextInt(42)

fun printAllUppercase(data: List<String>) {
    val result = data.filter { it.all { c -> c.isUpperCase() } }
        .ifEmpty { listOf("<대문자로만 이뤄진 원소 없음>") }
    result.forEach { println(it) }
}

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

val isEven = IntPredicate { it % 2 == 0 }

fun main() {
    foo("test")
    getBody()

    println(Foo.foo())
    println(Foo.bar)

    println(number)

    val sourceArr = arrayOf("k", "o", "t", "l", "i", "n")
    val targetArr = sourceArr.copyInto(arrayOfNulls(6), 3, 3, 6)
    println(targetArr.contentToString())

    sourceArr.copyInto(targetArr, startIndex = 0, endIndex = 2)
    println(targetArr.contentToString())

    val keys = 'a'..'f'
    val map = keys.associateWith { it.toString().repeat(5).replaceFirstChar { char -> char.uppercase() } }
    println(map)

    printAllUppercase(listOf("foo", "bar"))
    printAllUppercase(listOf("FOO", "BAR"))

    val ints = (1..4).asSequence()
    println(ints.fold(0) { total, num -> total + num })

    val sequence = ints.scan(0) { total, num -> total + num }
    println(sequence.toList())

    println("Is 7 even? - ${isEven.accept(7)}")
    println("Is 6 even? - ${isEven.accept(6)}")
}
