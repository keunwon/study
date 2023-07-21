package com.keunwon.algorithm.baekjoon

/**
 * Title: 수들의 합 5
 * Level: 실버-5
 **/
class Problem2018 {
    fun solution(n: Int): Int {
        var answer = 0
        var start = 0
        var right = 0
        var sum = 0

        while (start <= n) {
            when {
                n < sum -> {
                    sum -= start
                    start++
                }
                n > sum -> {
                    right++
                    sum += right
                }
                n == sum -> {
                    answer++
                    right++
                    sum += right
                }
            }
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    Problem2018().solution(n).also { println(it) }
}
