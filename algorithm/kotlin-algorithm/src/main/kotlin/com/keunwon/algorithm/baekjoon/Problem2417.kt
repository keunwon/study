package com.keunwon.algorithm.baekjoon

import kotlin.math.pow

/**
 * Title: 정수 제곱근
 * Level: 실버-4
 **/
class Problem2417 {
    fun solution(n: Long): Long {
        var left = 0L
        var right = n

        while (left <= right) {
            val mid = (left + right) / 2

            if (n <= mid.toDouble().pow(2)) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() {
    val n = readLine()!!.toLong()
    Problem2417().solution(n).also { println(it) }
}
