package com.keunwon.algorithm.programmers

import kotlin.math.sqrt

/**
 * Title: 정수 제곱근 판별
 * Level: 1
 **/
class Lessons12934 {
    fun solution(n: Long): Long {
        val tmp = sqrt(n.toDouble())
        return if (tmp % 1 > 0) -1 else (tmp.toLong() + 1).let { it * it }
    }
}
