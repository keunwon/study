package com.keunwon.algorithm.baekjoon

import kotlin.math.max

/**
 * Title: 로봇 조종하기
 * Level: 골드-2
 **/
class Problem2169 {
    fun solution(map: Array<IntArray>): Int {
        val dp = Array(map.size) { IntArray(map[0].size) }.apply { this[0][0] = map[0][0] }

        for (i in 1 until map[0].size) {
            dp[0][i] = dp[0][i - 1] + map[0][i]
        }
        for (i in 1 until map.size) {
            val tmp1 = IntArray(map[0].size).apply {
                this[0] = dp[i - 1][0] + map[i][0]
            }
            val tmp2 = IntArray(map[0].size).apply {
                this[lastIndex] = dp[i - 1][lastIndex] + map[i][lastIndex]
            }

            for (j in 1 until tmp1.size) {
                tmp1[j] = max(tmp1[j - 1], dp[i - 1][j]) + map[i][j]
            }
            for (j in tmp2.lastIndex - 1 downTo 0) {
                tmp2[j] = max(tmp2[j + 1], dp[i - 1][j]) + map[i][j]
            }

            for (j in tmp1.indices) {
                dp[i][j] = max(tmp1[j], tmp2[j])
            }
        }
        return dp[map.lastIndex][map[0].lastIndex]
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

    Problem2169().solution(map).also { println(it) }
}
