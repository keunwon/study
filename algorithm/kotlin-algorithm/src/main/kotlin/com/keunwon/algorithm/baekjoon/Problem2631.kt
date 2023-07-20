package com.keunwon.algorithm.baekjoon

/**
 * Title: 줄세우기
 * Level: 골드-4
 **/
class Problem2631 {
    fun solution(arr: IntArray): Int {
        val dp = IntArray(arr.size).apply { set(0, 1) }

        for (i in 1 until arr.size) {
            dp[i] = 1
            for (j in 0 until i) {
                if (arr[i] > arr[j]) dp[i] = dp[i].coerceAtLeast(dp[j] + 1)
            }
        }
        return arr.size - dp.maxOf { it }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = IntArray(n) { readLine()!!.toInt() }

    Problem2631().solution(arr).also { println(it) }
}
