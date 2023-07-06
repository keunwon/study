package com.keunwon.algorithm.programmers

/**
 * Title:실패율
 * Level: 1
 **/
class Lessons42889 {
    fun solution(N: Int, stages: IntArray): IntArray {
        val steps = (1..N).map { i ->
            val challenge = stages.count { it == i }
            val total = stages.count { it >= i }
            i to challenge.toDouble() / total
        }.sortedWith(compareBy({ -it.second }, { it.first }))
        return steps.map { it.first }.toIntArray()
    }
}
