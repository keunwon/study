package com.keunwon.algorithm.programmers

import kotlin.math.sqrt

class Lesson92335 {
    fun solution(n: Int, k: Int): Int {
        val arr = n.toString(k).split("0").filter { it.isNotBlank() }
        return arr.count { isPrime(it.toLong()) }
    }

    private fun isPrime(n: Long): Boolean {
        if (n < 2) return false
        return (2..sqrt(n.toDouble()).toInt()).none { n % it == 0L }
    }
}
