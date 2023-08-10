package com.keunwon.algorithm.againresolve

import java.util.*

/**
 * Title: 2차원 배열의 합
 * Level: 실버-5
 **/
class AProblem2167 {
    fun solution(map: Array<IntArray>, ranges: Array<IntArray>): IntArray {
        val prefixSum = createPrefixSumArray(map)

        return ranges.map { (r1, c1, r2, c2) ->
            val tmp = prefixSum[r1 - 1][c2] + prefixSum[r2][c1 - 1] + prefixSum[r1 - 1][c1 - 1]
            prefixSum[r2][c2] - tmp
        }.toIntArray()
    }

    private fun createPrefixSumArray(map: Array<IntArray>): Array<IntArray> {
        val prefixSum = Array(map.size + 1) { IntArray(map[0].size + 1) }

        for (i in 1..map.size) {
            for (j in 1..map[0].size) {
                prefixSum[i][j] =
                    prefixSum[i - 1][j] + prefixSum[i][j - 1] + map[i - 1][j - 1] - prefixSum[i - 1][j - 1]
            }
        }
        return prefixSum
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val map = Array(n) {
        val st = StringTokenizer(readLine()!!)
        IntArray(m) { st.nextToken().toInt() }
    }
    val k = readLine()!!.toInt()
    val ranges = Array(k) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    AProblem2167().solution(map, ranges).forEach(::println)
}
