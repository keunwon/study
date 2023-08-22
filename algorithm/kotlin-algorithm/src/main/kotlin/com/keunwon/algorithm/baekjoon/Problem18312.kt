package com.keunwon.algorithm.baekjoon

/**
 * Title: 시각
 * Level: 브론즈-2
 **/
class Problem18312 {
    fun solution(n: Int, k: Int): Int {
        var count = 0

        for (h in 0..n) {
            for (m in 0..59) {
                for (s in 0..59) {
                    val valid = intArrayOf(h, m, s)
                        .map { it.timeToString() }
                        .any { it.contains("$k") }
                    if (valid) count++
                }
            }
        }
        return count
    }

    private fun Int.timeToString(): String = "0".repeat(2 - "$this".length) + this
}

fun main() {
    val (n, k) = readLine()!!.split(" ").map { it.toInt() }
    Problem18312().solution(n, k).also(::println)
}
