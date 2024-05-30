package com.keunwon.algorithm.programmers

class Lesson42842 {
    fun solution(brown: Int, yellow: Int): IntArray {
        val answer = intArrayOf(0, 0)

        for (i in 1..yellow) {
            if (yellow % i != 0) continue

            val j = yellow / i
            val sum = (j + 2) * 2 + i * 2

            if (sum == brown) {
                answer[0] = j + 2
                answer[1] = i + 2
                break
            }
        }
        return answer
    }
}
