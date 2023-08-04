package com.keunwon.algorithm.againresolve

import kotlin.math.pow
import kotlin.math.sqrt

class ALessons140107 {
    fun solution(k: Int, d: Int): Long {
        var answer = 0L
        for (x in 0..d step k) {
            val y = sqrt(d.toDouble().pow(2) - x.toDouble().pow(2)).toInt()
            answer += y / k + 1
        }
        return answer
    }
}

fun main() {
    val k = 2
    val d = 4
    ALessons140107().solution(k, d).also(::println)
}
