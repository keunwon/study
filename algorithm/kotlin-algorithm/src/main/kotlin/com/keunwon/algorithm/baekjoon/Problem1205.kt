package com.keunwon.algorithm.baekjoon

/**
 * Title: 등수 구하기
 * Level: 실버-4
 **/
class Problem1205 {
    fun solution(target: Int, p: Int, scores: IntArray): Int {
        if (p == scores.size && target <= scores.last()) return -1

        var rank = 1
        for (score in scores) {
            if (target < score) rank++ else break
        }
        return rank
    }
}

fun main() {
    val (n, target, p) = readLine()!!.split(" ").map { it.toInt() }
    if (n == 0) return println(1)
    val scores = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem1205().solution(target, p, scores).also { println(it) }
}
