package com.keunwon.algorithm.againresolve

class ALessons178870 {
    fun solution(sequence: IntArray, k: Int): IntArray {
        val answer = intArrayOf(0, sequence.lastIndex)
        var startIndex = 0
        val dp = IntArray(sequence.size).apply { set(0, sequence[0]) }

        for (i in 1 until sequence.size) {
            dp[i] = dp[i - 1] + sequence[i]
            while (k < dp[i]) {
                dp[i] -= sequence[startIndex++]
            }

            if (dp[i] == k && i - startIndex < answer[1] - answer[0]) {
                answer[0] = startIndex
                answer[1] = i
            }
        }
        return answer
    }
}

fun main() {
    ALessons178870().solution(
        intArrayOf(1, 2, 3, 4, 5),
        7
    ).also { println(it.contentToString()) }

    ALessons178870().solution(
        intArrayOf(1, 1, 1, 2, 3, 4, 5),
        5
    ).also { println(it.contentToString()) }

    ALessons178870().solution(
        intArrayOf(2, 2, 2, 2, 2),
        6
    ).also { println(it.contentToString()) }
}
