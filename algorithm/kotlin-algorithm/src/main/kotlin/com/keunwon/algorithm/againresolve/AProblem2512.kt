package com.keunwon.algorithm.againresolve

/**
 * Title: 예산
 * Level: 실버-2
 **/
class AProblem2512 {
    fun solution(arr: IntArray, m: Int): Int {
        arr.sort()

        var left = 0
        var right = arr.last()

        while (left <= right) {
            val mid = (left + right) / 2
            val sum = arr.sumOf { it.coerceAtMost(mid) }

            if (sum <= m) left = mid + 1
            else right = mid - 1
        }
        return right
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val m = readLine()!!.toInt()

    AProblem2512().solution(arr, m).also(::println)
}
