package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 임스와 함께하는 미니게임
 * 난이도: 실버-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/25757">임스와 함께하는 미니게임 (실버-5)</a>
 **/
class Problem25757 {
    fun solution(type: Char, names: Array<String>): Int {
        val set = names.toSet()
        return when (type) {
            'Y' -> set.size
            'F' -> set.size / 2
            'O' -> set.size / 3
            else -> set.size
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()

    val (n, type) = br.readLine().split(" ")
    val names = Array(n.toInt()) { br.readLine() }
    Problem25757().solution(type[0], names).also { println(it) }
}
