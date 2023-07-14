package com.keunwon.algorithm.baekjoon

/**
 * Title: 임스와 함께하는 미니게임
 * Level: 실버-5
 **/
class Problem25757 {
    fun solution(type: String, players: Array<String>): Int {
        val size = when (type) {
            "Y" -> 1
            "F" -> 2
            "O" -> 3
            else -> error("")
        }
        val playerSet = players.toSet()
        return playerSet.size / size
    }
}

fun main() {
    val (n, type) = readLine()!!.split(" ").let { it[0].toInt() to it[1] }
    val players = Array(n) { readLine()!! }

    Problem25757().solution(type, players).also { println(it) }
}
