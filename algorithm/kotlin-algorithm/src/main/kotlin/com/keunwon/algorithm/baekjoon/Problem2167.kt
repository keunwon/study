package com.keunwon.algorithm.baekjoon

/**
 * Title: 2차원 배열의 합
 * Level: 실버-5
 **/
class Problem2167 {
    fun solution(arr: Array<IntArray>, ranges: Array<IntArray>): IntArray {
        val prefixSum = Array(arr.size + 1) { IntArray(arr[0].size + 1) }

        for (i in 1..arr.size) {
            for (j in 1..arr[0].size) {
                prefixSum[i][j] =
                    arr[i - 1][j - 1] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1]
            }
        }
        return ranges.map { (i, j, x, y) ->
            val tmp = prefixSum[i - 1][y] + prefixSum[x][j - 1] - prefixSum[i - 1][j - 1]
            prefixSum[x][y] - tmp
        }.toIntArray()
    }
}

fun main() {
    val readIntArray = { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(n) { readIntArray() }
    val k = readLine()!!.toInt()
    val ranges = Array(k) { readIntArray() }

    Problem2167().solution(arr, ranges).forEach { println(it) }
}
