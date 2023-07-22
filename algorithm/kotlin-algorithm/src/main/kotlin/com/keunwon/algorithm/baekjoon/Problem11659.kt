package com.keunwon.algorithm.baekjoon

/**
 * Title: 구간 합 구하기 4
 * Level: 실버-3
 **/
class Problem11659 {
    fun solution(arr: IntArray, ranges: Array<Pair<Int, Int>>): IntArray {
        val prefixSum = IntArray(arr.size + 1)

        for (i in 1 until prefixSum.size) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1]
        }
        return ranges.map { (start, end) ->
            prefixSum[end] - prefixSum[start - 1]
        }.toIntArray()
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val ranges = Array(m) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .let { it[0] to it[1] }
    }

    Problem11659().solution(arr, ranges).forEach { println(it) }
}
