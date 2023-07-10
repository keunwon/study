package com.keunwon.algorithm.baekjoon

/**
 * Title: 게임
 * Level: 실버-3
 **/
class Problem1072 {
    fun solution(x: Long, y: Long): Long {
        val z = 100 * y / x
        if (z == 99L || z == 100L) return -1

        var left = 1L
        var right = x

        while (left <= right) {
            val mid = (left + right) / 2
            val tmp = 100 * (y + mid) / (x + mid)

            if (z < tmp) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() {
    val (x, y) = readLine()!!.split(" ").map { it.toLong() }
    Problem1072().solution(x, y).also { println(it) }
}
