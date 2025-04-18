package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 빌런 호석
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/22251">빌런 호석 (골드-5)</a>
 **/
class Problem22251 {
    private val lights = arrayOf(
        booleanArrayOf(true, true, true, false, true, true, true),
        booleanArrayOf(false, false, true, false, true, false, false),
        booleanArrayOf(false, true, true, true, false, true, true),
        booleanArrayOf(false, true, true, true, true, true, false),
        booleanArrayOf(true, false, true, true, true, false, false),
        booleanArrayOf(true, true, false, true, true, true, false),
        booleanArrayOf(true, true, false, true, true, true, true),
        booleanArrayOf(false, true, true, false, true, false, false),
        booleanArrayOf(true, true, true, true, true, true, true),
        booleanArrayOf(true, true, true, true, true, true, false),
    )

    fun solution(n: Int, k: Int, p: Int, x: Int): Int {
        val target = IntArray(k).apply {
            "$x".reversed().forEachIndexed { index, c -> this[lastIndex - index] = c - '0' }
        }
        var result = 0

        for (floor in 1..n) {
            if (floor == x) continue

            val next = IntArray(k).apply {
                "$floor".reversed().forEachIndexed { index, c -> this[lastIndex - index] = c - '0' }
            }
            var count = 0

            for (i in 0 until k) {
                val a = target[i]
                val b = next[i]

                for (j in lights[0].indices) {
                    if (lights[a][j] != lights[b][j]) ++count
                    if (count > p) break
                }
                if (count > p) break
            }

            if (count <= p) ++result
        }
        return result
    }
}

fun main() {
    val (n, k, p, x) = readln().split(" ").map { it.toInt() }
    Problem22251().solution(n, k, p, x).also { println(it) }
}
