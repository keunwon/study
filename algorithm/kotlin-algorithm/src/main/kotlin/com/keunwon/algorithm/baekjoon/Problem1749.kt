package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 점수따먹기
 * Level: 골드-4
 **/
class Problem1749 {
    fun solution(map: Array<IntArray>): Int {
        val prefixSum = Array(map.size + 1) { IntArray(map[0].size + 1) }

        for (i in 1 until prefixSum.size) {
            for (j in 1 until prefixSum[0].size) {
                val tmp = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + map[i - 1][j - 1]
                prefixSum[i][j] = tmp
            }
        }

        var answer = Int.MIN_VALUE

        for (r1 in 1 until prefixSum.size) {
            for (c1 in 1 until prefixSum[0].size) {
                for (r2 in r1 until prefixSum.size) {
                    for (c2 in c1 until prefixSum[0].size) {
                        val num =
                            prefixSum[r2][c2] - prefixSum[r1 - 1][c2] - prefixSum[r2][c1 - 1] + prefixSum[r1 - 1][c1 - 1]
                        answer = answer.coerceAtLeast(num)
                    }
                }
            }
        }
        return answer
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) {
        val st = StringTokenizer(readLine()!!)
        IntArray(m) { st.nextToken().toInt() }
    }

    Problem1749().solution(map).also(::println)
}
