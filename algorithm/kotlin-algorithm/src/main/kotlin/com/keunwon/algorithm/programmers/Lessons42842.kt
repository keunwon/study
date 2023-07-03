package com.keunwon.algorithm.programmers

/**
 * Title: 카펫
 * Level: 2
 **/
class Lessons42842 {
    fun solution(brown: Int, yellow: Int): IntArray {
        val answer = IntArray(2)
        for (i in 1..yellow) {
            if (yellow % i != 0) continue

            val j = yellow / i + 2
            val num = i * 2 + j * 2

            if (brown == num) {
                answer[0] = j
                answer[1] = i + 2
                break
            }
        }
        return answer
    }
}
