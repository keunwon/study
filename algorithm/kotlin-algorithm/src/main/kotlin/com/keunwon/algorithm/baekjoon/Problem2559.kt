package com.keunwon.algorithm.baekjoon

/**
 * Title: 수열
 * Level: 실버-3
 **/
class Problem2559 {
    fun solution(k: Int, arr: IntArray): Int {
        val prefixSum = IntArray(arr.size + 1)

        for (i in arr.indices) {
            prefixSum[i + 1] = prefixSum[i] + arr[i]
        }

        var max = Int.MAX_VALUE
        for (i in k until prefixSum.size) {
            val sum = prefixSum[i] - prefixSum[i - k]
            max = max.coerceAtLeast(sum)
        }
        return max
    }
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem2559().solution(k, arr).also { println(it) }
}
