package com.keunwon.algorithm.programmers

import kotlin.math.pow
import kotlin.math.sqrt

class Lesson12934 {
    fun solution(n: Long): Long {
        val sqrt = sqrt(n.toDouble())
        val num = sqrt.toLong()
        return if (sqrt == num.toDouble()) (sqrt + 1).pow(2).toLong() else -1L
    }
}
