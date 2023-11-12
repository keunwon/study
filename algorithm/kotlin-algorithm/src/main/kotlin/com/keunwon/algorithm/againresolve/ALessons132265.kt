package com.keunwon.algorithm.againresolve

class ALessons132265 {
    fun solution(topping: IntArray): Int {
        val countMap = topping.toList()
            .groupingBy { it }
            .eachCount()
            .toMutableMap()
        val set = mutableSetOf<Int>()

        return topping.count { n ->
            set.add(n)
            countMap.computeIfPresent(n) { _, v -> v.dec().takeIf { it > 0 } }
            countMap.size == set.size
        }
    }
}

fun main() {
    ALessons132265().solution(
        intArrayOf(1, 2, 1, 3, 1, 4, 1, 2)
    ).let(::println)

    ALessons132265().solution(
        intArrayOf(1, 2, 3, 1, 4)
    ).let(::println)
}
