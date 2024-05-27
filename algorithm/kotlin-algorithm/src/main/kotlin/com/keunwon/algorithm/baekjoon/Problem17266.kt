package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem17266 {
    fun solution(n: Int, positions: IntArray): Int {
        var left = 1
        var right = n

        while (left <= right) {
            val height = (left + right) / 2

            if (check(n, height, positions)) right = height - 1
            else left = height + 1
        }
        return left
    }

    private fun check(n: Int, height: Int, position: IntArray): Boolean {
        var prev = 0
        for (p in position) {
            if (p - height > prev) return false
            prev = p + height
        }
        return n <= prev
    }
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val positions = run {
        val st = StringTokenizer(readln())
        IntArray(m) { st.nextToken().toInt() }
    }

    println(Problem17266().solution(n, positions))
}
