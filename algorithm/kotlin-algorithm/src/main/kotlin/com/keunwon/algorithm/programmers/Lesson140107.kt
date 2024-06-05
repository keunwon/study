package com.keunwon.algorithm.programmers

import kotlin.math.sqrt

class Lesson140107 {
    fun solution(k: Int, d: Int): Long {
        var answer = 0L
        for (x in 0..d step k) {
            val n = ((d.toLong() * d) - (x.toLong() * x))
            val y = sqrt(n.toDouble()).toLong()
            answer += y / k + 1
        }
        return answer
    }
}
