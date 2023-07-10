package com.keunwon.algorithm.baekjoon

/**
 * Title: 기타 레슨
 * Level: 실버-1
 **/
class Problem2343 {
    fun solution(m: Int, arr: IntArray): Int {
        var left = arr.maxOf { it }
        var right = arr.sum()

        while (left <= right) {
            val mid = (left + right) / 2
            val count = getCount(mid, arr)

            if (m >= count) right = mid - 1
            else left = mid + 1
        }
        return left
    }

    private fun getCount(limit: Int, arr: IntArray): Int {
        var count = 0
        var sum = 0

        for (num in arr) {
            if (limit < sum + num) {
                sum = 0
                count++
            }
            sum += num
        }
        return if (sum == 0) count else count + 1
    }
}

fun main() {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem2343().solution(m, arr)
}
