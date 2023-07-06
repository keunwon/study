package com.keunwon.algorithm.programmers

import kotlin.math.sqrt

/**
 * Title: 기사단원의 무기
 * Level: 1
 **/
class Lessons136798 {
    fun solution(number: Int, limit: Int, power: Int): Int {
        return (1..number).map { countByMeasure(it) }
            .sumOf { if (it <= limit) it else power }
    }

    private fun countByMeasure(n: Int): Int {
        val last = sqrt(n.toDouble()).toInt()
        return (1..last).sumOf {
            if (n % it != 0) 0
            else 1 + if (n / it == it) 0 else 1
        }
    }
}
