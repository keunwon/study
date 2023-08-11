package com.keunwon.algorithm.baekjoon

/**
 * Title: 나머지 합
 * Level: 골드-3
 **/
class Problem10986 {
    fun solution(m: Int, arr: IntArray): Long {
        val prefixSum = IntArray(arr.size + 1).apply {
            for (i in 1..arr.size) {
                this[i] = this[i - 1] + arr[i - 1]
            }
        }

        var answer = 0L
        for (i in 1..arr.size) {
            if (prefixSum[i] % m == 0) answer++

            for (j in 1 until i) {
                val num = prefixSum[i] - prefixSum[j]
                if (num % m == 0) answer++
            }
        }
        return answer
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem10986().solution(m, arr).also(::println)
}
