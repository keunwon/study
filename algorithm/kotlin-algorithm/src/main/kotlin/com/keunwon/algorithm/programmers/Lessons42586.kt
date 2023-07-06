package com.keunwon.algorithm.programmers

import kotlin.math.ceil

/**
 * Title: 기능개발
 * Level: 2
 **/
class Lessons42586 {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val days = progresses.indices
            .map { ceil((100.0 - progresses[it]) / speeds[it]) }
            .toMutableList()
        val answer = mutableListOf<Int>()

        while (days.isNotEmpty()) {
            val cur = days.removeFirst()
            var count = 1

            while (days.isNotEmpty() && days.first() <= cur) {
                days.removeFirst()
                count++
            }
            answer.add(count)
        }
        return answer.toIntArray()
    }
}
