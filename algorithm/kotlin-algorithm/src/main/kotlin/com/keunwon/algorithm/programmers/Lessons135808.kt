package com.keunwon.algorithm.programmers

/**
 * Title: 과일 장수
 * Level: 1
 **/
class Lessons135808 {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        return score.sortedDescending()
            .take(score.size / m * m)
            .chunked(m)
            .sumOf { arr -> arr.minOf { it } * m }
    }
}
