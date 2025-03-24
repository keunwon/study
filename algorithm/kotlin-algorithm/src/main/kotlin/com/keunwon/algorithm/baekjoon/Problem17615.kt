package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 볼 모으기
 * 난이도: 실버-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17615">볼 모으기 (실버-1)</a>
 **/
class Problem17615 {
    fun solution(str: String): Int {
        val redCount = str.count { it == 'R' }
        val blueCount = str.count { it == 'B' }

        return (redCount - sameCount(str, 'R'))
            .coerceAtMost(blueCount - sameCount(str, 'B'))
            .coerceAtMost(redCount - sameCount(str.reversed(), 'R'))
            .coerceAtMost(blueCount - sameCount(str.reversed(), 'B'))
    }

    private fun sameCount(str: String, type: Char): Int {
        var count = 0
        for (c in str) {
            if (c != type) break
            ++count
        }
        return count
    }
}

fun main() {
    val n = readln().toInt()
    val str = readln()

    Problem17615().solution(str).also { println(it) }
}
