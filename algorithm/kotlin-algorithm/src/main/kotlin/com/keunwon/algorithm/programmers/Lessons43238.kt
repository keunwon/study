package com.keunwon.algorithm.programmers

/**
 * Title: 입국심사
 * Level: 3
 **/
class Lessons43238 {
    fun solution(n: Int, times: IntArray): Long {
        times.sort()

        var left = times.first().toLong()
        var right = times.last().toLong() * n

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = times.sumOf { mid / it }

            if (n <= sum) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}
