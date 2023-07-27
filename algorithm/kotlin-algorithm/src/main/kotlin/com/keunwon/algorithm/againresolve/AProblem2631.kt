package com.keunwon.algorithm.againresolve

class AProblem2631 {
    fun solution(arr: IntArray): Int {
        val dp = IntArray(arr.size) { 1 }

        for (i in 1 until arr.size) {
            for (j in 0 until i) {
                if (arr[i] > arr[j]) {
                    dp[i] = dp[i].coerceAtLeast(dp[j] + 1)
                }
            }
        }
        return arr.size - dp.maxOf { it }
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = IntArray(n) { readLine()!!.toInt() }

    AProblem2631().solution(arr).also { println(it) }
}
