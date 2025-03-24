package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 부분합
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1806">부분합 (골드-4)</a>
 **/
class Problem1806 {
    fun solution(s: Int, numbers: IntArray): Int {
        var left = 0
        var right = 0
        var sum = 0
        var min = 1e9.toInt()

        while (right <= numbers.size) {
            if (s <= sum) {
                min = min.coerceAtMost(right - left)
                sum -= numbers[left++]
            } else {
                if (right == numbers.size) break
                sum += numbers[right++]
            }
        }
        return if (min == 1e9.toInt()) 0 else min
    }
}

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val numbers = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }
    Problem1806().solution(s, numbers).also { println(it) }
}
