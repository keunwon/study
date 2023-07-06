package com.keunwon.algorithm.programmers

/**
 * Title: 숫자 짝꿍
 * Level: 1
 **/
class Lessons131128 {
    fun solution(X: String, Y: String): String {
        val xCount = X.groupingBy { it }.eachCount()
        val yCount = Y.groupingBy { it }.eachCount()

        val answer = mutableListOf<Int>()
        for ((n, count) in xCount) {
            if (!yCount.contains(n)) continue

            val size = count.coerceAtMost(yCount.getValue(n))
            repeat(size) { answer.add(n.digitToInt()) }
        }
        return when {
            answer.isEmpty() -> "-1"
            answer.all { it == 0 } -> "0"
            else -> answer.sortedDescending().joinToString("")
        }
    }
}
