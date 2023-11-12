package com.keunwon.algorithm.againresolve

class ALessons42747 {
    fun solution(citations: IntArray): Int {
        citations.sort()
        for ((i, citation) in citations.withIndex()) {
            val hIndex = citations.size - i
            if (hIndex <= citation) return hIndex
        }
        return 0
    }
}

fun main() {
    ALessons42747().solution(intArrayOf(3, 0, 6, 1, 5)).also(::println)
}
