package com.keunwon.algorithm.baekjoon

/**
 * Title: 부분합
 * Level: 골드-4
 **/
class Problem1806 {
    fun solution(s: Int, arr: IntArray): Int {
        var (left, right) = 0 to 0
        var min = Int.MAX_VALUE
        var total = 0

        while (right < arr.size) {
            if (s <= total) {
                min = min.coerceAtMost(right - left)
                total -= arr[left++]
            } else total += arr[right++]
        }
        return if (min == Int.MAX_VALUE) 0 else min
    }
}

fun main() {
    val (n, s) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ")
        .map { it.toInt() }
        .plus(0)
        .toIntArray()

    Problem1806().solution(s, arr).also { println(it) }
}
