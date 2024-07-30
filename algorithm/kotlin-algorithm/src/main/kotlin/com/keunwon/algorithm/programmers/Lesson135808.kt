package com.keunwon.algorithm.programmers

class Lesson135808 {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        val buckets = score.sortedByDescending { it }.chunked(m)
        return buckets.sumOf { if (it.size == m) it.last() * m else 0 }
    }
}
