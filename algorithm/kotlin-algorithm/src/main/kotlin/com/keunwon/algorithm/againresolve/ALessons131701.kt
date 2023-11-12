package com.keunwon.algorithm.againresolve

class ALessons131701 {
    fun solution(elements: IntArray): Int {
        val arr = elements + elements
        val set = mutableSetOf<Int>()

        for (size in 1..elements.size) {
            for (i in elements.indices) {
                val sum = arr.slice(i until i + size).sum()
                set.add(sum)
            }
        }
        return set.count()
    }
}

fun main() {
    ALessons131701().solution(intArrayOf(7, 9, 1, 1, 4)).also(::println)
}
