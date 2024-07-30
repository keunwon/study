package com.keunwon.algorithm.programmers

class Lesson77484 {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val numbers = lottos.filter { win_nums.contains(it) }.toSet()
        val zeroCount = lottos.count { it == 0 }
        val rank = intArrayOf(6, 6, 5, 4, 3, 2, 1)

        return intArrayOf(
            rank[numbers.size + zeroCount],
            rank[numbers.size]
        )
    }
}
