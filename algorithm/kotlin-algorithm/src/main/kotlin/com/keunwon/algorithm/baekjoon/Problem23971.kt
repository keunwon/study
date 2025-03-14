package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름:ZOAC 4
 * 난이도:브론즈-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/23971">ZOAC 4 (브론즈-3)</a>
 **/
class Problem23971 {
    fun solution(h: Int, w: Int, n: Int, m: Int): Int {
        val height = (h - 1) / (n + 1) + 1
        val weight = (w - 1) / (m + 1) + 1
        return height * weight
    }
}

fun main() {
    val (h, w, n, m) = readln().split(" ").map { it.toInt() }
    Problem23971().solution(h, w, n, m).also { println(it) }
}
