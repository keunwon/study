package com.keunwon.algorithm.programmers

/**
 * Title: 로또의 최고 순위와 최저 순위
 * Level: 1
 **/
class Lessons77484 {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val rank = mapOf(6 to 1, 5 to 2, 4 to 3, 3 to 4, 2 to 5)
        val matchCount = lottos.count { it in win_nums }
        val zeroCount = lottos.count { it == 0 }
        return intArrayOf(
            rank.getOrDefault(matchCount + zeroCount, 6),
            rank.getOrDefault(matchCount, 6),
        )
    }
}
