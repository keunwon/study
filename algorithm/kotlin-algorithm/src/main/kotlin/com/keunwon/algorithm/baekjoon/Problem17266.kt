package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 어두운 굴다리
 * 난이도: 실버-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17266">어두운 굴다리 (실버-4)</a>
 **/
class Problem17266 {
    fun solution(n: Int, positions: IntArray): Int {
        var left = 1
        var right = n

        while (left <= right) {
            val h = left + (right - left) / 2
            var cur = 0
            var flag = true

            for (p in positions) {
                if (p - h > cur) {
                    flag = false
                    break
                }
                cur = p + h
            }
            if (flag) flag = n - cur <= 0
            if (flag) right = h - 1 else left = h + 1
        }
        return left
    }
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val positions = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(m) { arr[it] }
    }

    Problem17266().solution(n, positions).also { println(it) }
}
