package com.keunwon.algorithm.againresolve

class ALessons43238 {
    fun solution(n: Int, times: IntArray): Long {
        times.sort()

        var left = times[0].toLong()
        var right = times[1].toLong() * n
        while (left <= right) {
            val mid = (left + right) / 2
            val sum = times.sumOf { mid / it }

            if (n <= sum) right = mid - 1
            else left = mid + 1
        }
        return left
    }
}

fun main() {
    ALessons43238().solution(6, intArrayOf(7, 10)).also(::println)
}
