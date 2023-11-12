package com.keunwon.algorithm.againresolve

import kotlin.math.sqrt

class ALessons92335 {
    fun solution(n: Int, k: Int): Int = n.toString(k)
        .split("0")
        .filter { it.isNotBlank() }
        .count { isPrime(it.toLong()) }

    private fun isPrime(n: Long): Boolean {
        if (n < 2) return false
        return (2..sqrt(n.toDouble()).toInt()).none { n % it == 0L }
    }
}

fun main() {
    ALessons92335().solution(437674, 3).also(::println)
    ALessons92335().solution(110011, 10).also(::println)
}
