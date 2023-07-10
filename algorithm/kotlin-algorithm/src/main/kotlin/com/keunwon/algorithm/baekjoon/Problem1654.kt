package com.keunwon.algorithm.baekjoon

/**
 * Title: 랜선 자르기
 * Level: 실버-2
 **/
class Problem1654 {
    fun solution(arr: LongArray, n: Int): Long {
        arr.sort()

        var left = 1L
        var right = arr.last()

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = arr.sumOf { it / mid }

            if (n <= sum) left = mid + 1
            else right = mid - 1
        }
        return right
    }
}

fun main() {
    val (k, n) = readLine()!!.split(" ").map(String::toInt)
    val arr = LongArray(k) { readLine()!!.toLong() }

    Problem1654().solution(arr, n).also { println(it) }
}
