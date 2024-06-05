package com.keunwon.algorithm.programmers

class Lesson138475 {
    fun solution(e: Int, starts: IntArray): IntArray {
        val dp = IntArray(e + 1)
        for (i in 1..e) {
            for (j in 1..e) {
                val num = i * j
                if (num <= e) ++dp[num] else break
            }
        }

        val maxArr = IntArray(e + 1) { it }
        for (i in e - 1 downTo 0) {
            if (dp[i] >= dp[maxArr[i + 1]]) {
                maxArr[i] = i
            } else {
                maxArr[i] = maxArr[i + 1]
            }
        }
        return starts.map { s -> maxArr[s] }.toIntArray()
    }
}
