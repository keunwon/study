package com.keunwon.algorithm.programmers

class Lesson62048 {
    fun solution(w: Int, h: Int): Long {
        val gcd = gcd(w, h)
        return (w.toLong() * h) - ((w / gcd) + (h / gcd) - 1) * gcd
    }

    private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
}