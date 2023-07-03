package com.keunwon.algorithm.programmers

/**
 * Title: 멀리 뛰기
 * Level: 2
 **/
class Lessons12914 {
    fun solution(n: Int): Long {
        val dp = LongArray(n + 2).also { arr ->
            arr[1] = 1
            arr[2] = 2
            (3..n).forEach { arr[it] = (arr[it - 2] + arr[it - 1]) % 1234567 }
        }
        return dp[n]
    }
}
