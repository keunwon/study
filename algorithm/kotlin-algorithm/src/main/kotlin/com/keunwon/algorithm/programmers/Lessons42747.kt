package com.keunwon.algorithm.programmers

/**
 * Title: H-Index
 * Level: 2
 **/
class Lessons42747 {
    fun solution(citations: IntArray): Int {
        citations.sort()

        for (i in citations.indices) {
            val hIndex = citations.size - i
            if (hIndex <= citations[i]) return hIndex
        }
        return 0
    }
}
