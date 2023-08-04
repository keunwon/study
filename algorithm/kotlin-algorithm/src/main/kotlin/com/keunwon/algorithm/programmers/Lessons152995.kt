package com.keunwon.algorithm.programmers

/**
 * Title: 인사고과
 * Level: 3
 **/
class Lessons152995 {
    fun solution(scores: Array<IntArray>): Int {
        val whanho = scores[0]
        var answer = 1
        var maxScore = 0

        scores.sortWith(compareBy({ -it[0] }, { it[1] }))

        for ((left, right) in scores) {
            if (right < maxScore) {
                if (whanho[0] == left && whanho[1] == right) return -1
            } else {
                maxScore = maxScore.coerceAtLeast(right)
                if (whanho[0] + whanho[1] < left + right) answer++
            }
        }
        return answer
    }
}
