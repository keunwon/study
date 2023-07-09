package com.keunwon.algorithm.programmers

/**
 * Title: 멀쩡한 사각형
 * Level: 2
 **/
// todo
class Lessons62048 {
    fun solution(w: Int, h: Int): Long {
        val gcd = gcd(w, h)
        return (w.toLong() * h) - ((w / gcd) + (h / gcd) - 1) * gcd
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}
