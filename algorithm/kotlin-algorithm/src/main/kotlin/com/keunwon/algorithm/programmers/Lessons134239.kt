package com.keunwon.algorithm.programmers

/**
 * Title: 우박수열 정적분
 * Level: 2
 **/
class Lessons134239 {
    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        val list = generateSequence(k) { if (it % 2 == 0) it / 2 else it * 3 + 1 }
            .takeWhile { it > 1 }
            .plus(1)
            .toList()
        val prefixSum = DoubleArray(list.size).also { ps ->
            for (i in 1 until list.size) {
                ps[i] = ps[i - 1] + (list[i - 1] + list[i]) / 2.0
            }
        }

        val answer = DoubleArray(ranges.size)
        for ((i, range) in ranges.withIndex()) {
            val (start, end) = range[0] to prefixSum.lastIndex + range[1]

            if (start > end) answer[i] = -1.0
            else answer[i] = prefixSum[end] - prefixSum[start]
        }
        return answer
    }
}
