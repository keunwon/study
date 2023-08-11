package com.keunwon.algorithm.againresolve

/**
 * Title: 나무 자르기
 * Level: 실버-2
 **/
class AProblem2805 {
    fun solution(m: Int, arr: LongArray): Long {
        arr.sort()

        var left = 0L
        var right = arr.last()

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = arr.filter { mid <= it }.sumOf { it - mid }

            if (m <= sum) left = mid + 1
            else right = mid - 1
        }
        return right
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toLong() }.toLongArray()

    AProblem2805().solution(m, arr).also(::println)
}
