package com.keunwon.algorithm.againresolve

class ALessons42746 {
    fun solution(numbers: IntArray): String {
        return numbers
            .map(Int::toString)
            .sortedWith { s1, s2 -> "$s2$s1".compareTo("$s1$s2") }
            .joinToString("")
            .takeUnless { it[0] == '0' } ?: "0"
    }
}

fun main() {
    ALessons42746().solution(
        intArrayOf(6, 10, 2)
    ).let(::println)

    ALessons42746().solution(
        intArrayOf(3, 30, 34, 5, 9)
    ).let(::println)

    ALessons42746().solution(
        intArrayOf(0, 0, 0)
    ).let(::println)
}
