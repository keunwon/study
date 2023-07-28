package com.keunwon.algorithm.programmers

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Title: 두 원 사이의 정수 쌍
 * Level: 2
 **/
class Lessons181187 {
    fun solution(r1: Int, r2: Int): Long {
        var answer = 0L
        for (y in 1..r2) {
            val yy = y.toDouble().pow(2)
            val inner = ceil(sqrt(r1.toDouble().pow(2) - yy)).toInt()
            val outer = floor(sqrt(r2.toDouble().pow(2) - yy)).toInt()
            answer += outer - inner + 1
        }
        return answer * 4
    }
}

fun main() {
    Lessons181187().solution(2, 3).also { println(it) }
}
