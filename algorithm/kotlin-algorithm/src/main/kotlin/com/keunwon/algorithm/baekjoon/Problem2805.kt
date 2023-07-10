package com.keunwon.algorithm.baekjoon

/**
 * Title: 나무 자르기
 * Level: 실버-2
 **/
class Problem2805 {
    fun solution(m: Int, arr: LongArray): Long {
        arr.sort()

        var left = 1L
        var right = arr.last()

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = arr.sumOf {
                val tmp = it - mid
                if (tmp > 0) tmp else 0
            }

            if (m <= sum) left = mid + 1
            else right = mid - 1
        }
        return right
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toLong() }.toLongArray()

    Problem2805().solution(m, arr).also { println(it) }
}
