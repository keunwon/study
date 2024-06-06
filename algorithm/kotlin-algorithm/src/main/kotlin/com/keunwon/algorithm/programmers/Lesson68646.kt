package com.keunwon.algorithm.programmers

import kotlin.math.min

class Lesson68646 {
    fun solution(a: IntArray): Int {
        val left = IntArray(a.size)
        val right = IntArray(a.size)
        var min = Int.MAX_VALUE

        for ((i, num) in a.withIndex()) {
            min = min(min, num)
            left[i] = min
        }

        min = Int.MAX_VALUE
        for (i in a.lastIndex downTo 0) {
            min = min(min, a[i])
            right[i] = min
        }

        var answer = 0
        for ((i, num) in a.withIndex()) {
            if (left[i] < num && right[i] < num) continue
            ++answer
        }
        return answer
    }
}
