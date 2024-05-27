package com.keunwon.algorithm.baekjoon

class Problem25757 {
    fun solution(y: Char, words: Array<String>): Int {
        val countMap = mapOf(
            'Y' to 1,
            'F' to 2,
            'O' to 3,
        )
        val uniques = words.toSet()
        return uniques.size / countMap.getValue(y)
    }
}

fun main() {
    val (n, y) = readln().split(" ")
    val words = Array(n.toInt()) { readln() }

    println(Problem25757().solution(y.single(), words))
}
