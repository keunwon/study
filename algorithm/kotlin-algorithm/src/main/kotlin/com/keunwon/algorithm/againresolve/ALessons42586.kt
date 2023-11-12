package com.keunwon.algorithm.againresolve

import kotlin.math.ceil

class ALessons42586 {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val days = progresses.zip(speeds)
            .map { (p, s) -> ceil((100.0 - p) / s) }
            .toMutableList()
        val answer = mutableListOf<Int>()

        while (days.isNotEmpty()) {
            val cur = days.removeFirst()
            var count = 1

            while (days.isNotEmpty() && days.first() <= cur) {
                days.removeFirst()
                ++count
            }
            answer.add(count)
        }
        return answer.toIntArray()
    }
}

fun main() {
    ALessons42586().solution(
        intArrayOf(93, 30, 55),
        intArrayOf(1, 30, 5)
    ).also { println(it.contentToString()) }

    ALessons42586().solution(
        intArrayOf(95, 90, 99, 99, 80, 99),
        intArrayOf(1, 1, 1, 1, 1, 1)
    ).also { println(it.contentToString()) }
}
