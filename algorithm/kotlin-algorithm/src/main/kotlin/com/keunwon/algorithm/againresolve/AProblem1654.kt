package com.keunwon.algorithm.againresolve

/**
 * Title: 랜선 자르기
 * Level: 실버-2
 **/
class AProblem1654 {
    fun solution(n: Int, arr: LongArray): Long {
        arr.sort()

        var left = 0L
        var right = arr.last().toLong()

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
    val (k, n) = readLine()!!.split(" ").map { it.toInt() }
    val arr = LongArray(k) { readLine()!!.toLong() }

    AProblem1654().solution(n, arr).also(::println)
}
