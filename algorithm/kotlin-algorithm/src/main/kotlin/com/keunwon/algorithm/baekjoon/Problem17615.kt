package com.keunwon.algorithm.baekjoon

class Problem17615 {
    fun solution(w: String): Int {
        val rCount = w.count { it == 'R' }
        val bCount = w.count { it == 'B' }
        val (r1, r2) = counts(w, 'R')
        val (b1, b2) = counts(w, 'B')

        return (rCount - r1)
            .coerceAtMost(rCount - r2)
            .coerceAtMost(bCount - b1)
            .coerceAtMost(bCount - b2)
    }

    private fun counts(w: String, target: Char): Pair<Int, Int> {
        val c1 = generateSequence(0, Int::inc)
            .takeWhile { it in w.indices && w[it] == target }
            .count()
        val c2 = generateSequence(w.lastIndex, Int::dec)
            .takeWhile { it in w.indices && w[it] == target }
            .count()
        return c1 to c2
    }
}

fun main() {
    val n = readln()
    val w = readln()
    println(Problem17615().solution(w))
}
