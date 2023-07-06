package com.keunwon.algorithm.programmers

/**
 * Title: 연속 부분 수열 합의 개수
 * Level: 2
 **/
class Lessons131701 {
    fun solution(elements: IntArray): Int {
        val arr = elements + elements
        val answer = mutableSetOf<Int>()

        for (len in 1..elements.size) {
            for (i in elements.indices) {
                val sum = arr.slice(i until i + len).sum()
                answer.add(sum)
            }
        }
        return answer.size
    }
}
