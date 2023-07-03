package com.keunwon.algorithm.programmers

/**
 * Title: N개의 최소공배수
 * Level: 2
 **/
class Lessons12953 {
    fun solution(arr: IntArray): Int {
        return arr.fold(1) { acc, num -> (acc * num) / gcd(acc, num) }
    }

    private fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}
