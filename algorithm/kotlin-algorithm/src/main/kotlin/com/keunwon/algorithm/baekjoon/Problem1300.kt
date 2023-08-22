package com.keunwon.algorithm.baekjoon

/**
 * Title: K번째 수
 * Level: 골드-2
 **/
class Problem1300 {
    fun solution(n: Int, k: Int): Int {
        var left = 1
        var right = k

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = (1..n).sumOf { (mid / it).coerceAtMost(n) }

            if (k <= sum) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val k = readLine()!!.toInt()

    Problem1300().solution(n, k).also(::println)
}
