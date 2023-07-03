package com.keunwon.algorithm.programmers

/**
 * Title: 최대공약수와 최소공배수
 * Level: 1
 **/
class Lessons12940 {
    fun solution(n: Int, m: Int): IntArray {
        val gcd = gcd(n, m)
        return intArrayOf(gcd, n * m / gcd)
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}
