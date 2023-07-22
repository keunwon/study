package com.keunwon.algorithm.baekjoon

/**
 * Title: 순서쌍의 곱의 합
 * Level: 실버-4
 **/
class Problem13900 {
    fun solution(arr: LongArray): Long {
        val prefixSum = LongArray(arr.size)

        for (i in 1 until arr.size) {
            prefixSum[i] = prefixSum[i - 1] + arr[i]
        }

        var answer = 0L
        for (i in arr.indices) {
            val num = arr[i]
            answer += num * (prefixSum.last() - prefixSum[i])
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toLong() }.toLongArray()

    Problem13900().solution(arr).also { println(it) }
}
