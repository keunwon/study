package com.keunwon.algorithm.baekjoon

/**
 * Title: 겹치는 건 싫어
 * Level: 실버-1
 **/
class Problem20922 {
    fun solution(k: Int, numbers: IntArray): Int {
        val dp = IntArray(200_001)
        var left = 0
        var right = 0
        var max = 0

        while (right < numbers.size) {
            if (dp[numbers[right]] < k) {
                max = max.coerceAtLeast(right - left + 1)
                dp[numbers[right]]++
                right++
            } else {
                dp[numbers[left]]--
                left++
            }
        }
        return max
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val numbers = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem20922().solution(k, numbers).also { println(it) }
}
