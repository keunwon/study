package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 주유소
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/13305">주유소 (실버-3)</a>
 **/
class Problem13305 {
    fun solution(distances: IntArray, prices: IntArray): Long {
        var result = 0L
        var min = Long.MAX_VALUE

        for ((i, d) in distances.withIndex()) {
            if (min > prices[i]) min = prices[i].toLong()
            result += min * d
        }
        return result
    }
}

fun main() {
    val n = readln().toInt()
    val distances = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n - 1) { arr[it] }
    }
    val prices = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }

    Problem13305().solution(distances, prices).also { println(it) }
}
