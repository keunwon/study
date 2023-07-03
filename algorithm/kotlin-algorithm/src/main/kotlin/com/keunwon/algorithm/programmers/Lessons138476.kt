package com.keunwon.algorithm.programmers

/**
 * Title: 귤 고르기
 * Level: 2
 **/
class Lessons138476 {
    fun solution(k: Int, tangerine: IntArray): Int {
        val list = tangerine.toList()
            .groupingBy { it }
            .eachCount()
            .toList()
            .map { it.second }
            .sortedByDescending { it }
        var answer = 0
        var sum = 0

        for (n in list) {
            if (k <= sum) break
            sum += n
            answer++
        }
        return answer
    }
}
