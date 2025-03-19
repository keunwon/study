package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 예산
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2512">예산 (실버-2)</a>
 **/
class Problem2512 {
    fun solution(areas: IntArray, m: Int): Int {
        var left = 0
        var right = areas.maxOrNull()!!

        while (left <= right) {
            val mid = left + (right - left) / 2
            val sum = areas.sumOf { it.coerceAtMost(mid) }

            if (sum <= m) left = mid + 1 else right = mid - 1
        }
        return right
    }
}

fun main() {
    val n = readln().toInt()
    val areas = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }
    val m = readln().toInt()

    Problem2512().solution(areas, m).also { println(it) }
}
