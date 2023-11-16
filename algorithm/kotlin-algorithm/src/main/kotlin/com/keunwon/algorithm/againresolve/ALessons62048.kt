package com.keunwon.algorithm.againresolve

class ALessons62048 {
    fun solution(w: Int, h: Int): Long {
        val gcd = gcd(w, h)
        return (w.toLong() * h) - (w / gcd + h / gcd - 1) * gcd
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}

fun main() {
    ALessons62048().solution(8, 12).also(::println)
}
