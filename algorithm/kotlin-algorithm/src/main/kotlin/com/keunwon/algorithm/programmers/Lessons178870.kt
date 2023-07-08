package com.keunwon.algorithm.programmers

/**
 * Title: 연속된 부분 수열의 합
 * Level: 2
 **/
class Lessons178870 {
    fun solution(sequence: IntArray, k: Int): IntArray {
        val answer = intArrayOf(0, sequence.lastIndex)
        val dp = IntArray(sequence.size)
        var startIndex = 0

        for ((i, num) in sequence.withIndex()) {
            dp[i] += if (i == 0) num else dp[i - 1] + num

            while (dp[i] > k) {
                dp[i] -= sequence[startIndex]
                startIndex++
            }

            if (dp[i] == k) {
                if (i - startIndex < answer[1] - answer[0]) {
                    answer[0] = startIndex
                    answer[1] = i
                }
            }
        }
        return answer
    }
}
