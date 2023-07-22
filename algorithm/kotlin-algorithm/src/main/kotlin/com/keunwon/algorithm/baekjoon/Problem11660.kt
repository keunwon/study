package com.keunwon.algorithm.baekjoon

/**
 * Title: 구간 합 구하기 5
 * Level: 실버-1
 **/
class Problem11660 {
    fun solution(arr: Array<IntArray>, rage: Array<IntArray>): IntArray {
        val prefixSum = Array(arr.size + 1) { IntArray(arr[0].size + 1) }

        for (i in 1..arr.size) {
            for (j in 1..arr[0].size) {
                prefixSum[i][j] =
                    arr[i - 1][j - 1] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1]
            }
        }
        return rage.map { (x1, y1, x2, y2) ->
            val tmp = prefixSum[x1 - 1][y2] + prefixSum[x2][y1 - 1] - prefixSum[x1 - 1][y1 - 1]
            prefixSum[x2][y2] - tmp
        }.toIntArray()
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = Array(n) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }
    val rage = Array(m) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem11660().solution(arr, rage).also { println(it.joinToString("\n")) }
}
