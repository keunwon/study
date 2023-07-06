package com.keunwon.algorithm.programmers

import kotlin.math.sqrt

/**
 * Title: k진수에서 소수 개수 구하기
 * Level: 2
 **/
class Lessons92335 {
    fun solution(n: Int, k: Int): Int {
        return n.toString(k)
            .split("0")
            .filter { it.isNotBlank() }
            .count { isPrime(it.toLong()) }
    }

    private fun isPrime(n: Long): Boolean {
        if (n < 2) return false
        return (2L..sqrt(n.toDouble()).toLong()).none { n % it == 0L }
    }
}
