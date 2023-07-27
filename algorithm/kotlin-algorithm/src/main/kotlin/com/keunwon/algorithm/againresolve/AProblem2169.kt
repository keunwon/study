package com.keunwon.algorithm.againresolve

import kotlin.math.max

class AProblem2169 {
    fun solution(map: Array<IntArray>): Int {
        val dp = Array(map.size) { IntArray(map[0].size) }.also { arr ->
            arr[0][0] = map[0][0]
            for (i in 1 until map[0].size) arr[0][i] = arr[0][i - 1] + map[0][i]
        }

        for (i in 1 until map.size) {
            val tmp1 = IntArray(map[0].size).also { arr ->
                arr[0] = dp[i - 1][0] + map[i][0]

                for (j in 1 until arr.size) {
                    arr[j] = max(arr[j - 1], dp[i - 1][j]) + map[i][j]
                }
            }
            val tmp2 = IntArray(map[0].size).also { arr ->
                val lastIndex = arr.lastIndex
                arr[lastIndex] = dp[i - 1][lastIndex] + map[i][lastIndex]

                for (j in lastIndex - 1 downTo 0) {
                    arr[j] = max(arr[j + 1], dp[i - 1][j]) + map[i][j]
                }
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
    val arr = Array(n) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    AProblem2169().solution(arr).also { println(it) }
}
