package com.keunwon.algorithm.programmers

class Lesson120876 {
    fun solution(lines: Array<IntArray>): Int {
        val map = hashMapOf<Int, Int>().apply {
            lines.forEach { (a, b) ->
                (a until b).forEach { this[it] = getOrDefault(it, 0) + 1 }
            }
        }
        return map.count { it.value > 1 }
    }
}
