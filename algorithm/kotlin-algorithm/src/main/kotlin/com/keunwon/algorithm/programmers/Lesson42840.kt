package com.keunwon.algorithm.programmers

class Lesson42840 {
    fun solution(answers: IntArray): IntArray {
        val p1 = intArrayOf(1, 2, 3, 4, 5)
        val p2 = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
        val p3 = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        val scores = IntArray(3)

        for ((index, n) in answers.withIndex()) {
            if (n == p1[index % p1.size]) ++scores[0]
            if (n == p2[index % p2.size]) ++scores[1]
            if (n == p3[index % p3.size]) ++scores[2]
        }

        val max = scores.maxOrNull() ?: 0
        return scores.indices.filter { scores[it] == max }.map { it + 1 }.toIntArray()
    }
}
