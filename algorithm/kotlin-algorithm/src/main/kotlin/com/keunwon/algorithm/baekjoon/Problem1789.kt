package com.keunwon.algorithm.baekjoon

/**
 * Title: 수들의 합
 * Level: 실버-5
 **/
class Problem1789 {
    fun solution(n: Long): Int {
        var count = 0
        var sum = 0L

        while (sum <= n) {
            count++
            sum += count
        }
        return count - 1
    }
}

fun main() {
    val n = readLine()!!.toLong()
    Problem1789().solution(n).also(::println)
}
