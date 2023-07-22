package com.keunwon.algorithm.baekjoon

/**
 * Title: 합 구하기
 * Level: 실버-3
 **/
class Problem11441 {
    fun solution(arr: IntArray, ranges: Array<Pair<Int, Int>>): IntArray {
        val prefixSum = IntArray(arr.size + 1).apply {
            for (i in arr.indices) this[i + 1] = this[i] + arr[i]
        }
        return ranges.map { (start, end) -> prefixSum[end] - prefixSum[start - 1] }.toIntArray()
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val m = readLine()!!.toInt()
    val ranges = Array(m) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    Problem11441().solution(arr, ranges).forEach { println(it) }
}
