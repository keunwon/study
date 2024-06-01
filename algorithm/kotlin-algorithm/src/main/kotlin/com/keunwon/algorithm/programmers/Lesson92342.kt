package com.keunwon.algorithm.programmers

class Lesson92342 {
    private var max = Int.MIN_VALUE
    private val answer = mutableListOf<IntArray>()

    fun solution(n: Int, info: IntArray): IntArray {
        dfs(n, 0, 0, info, IntArray(info.size))
        return answer.maxByOrNull { it.reversed().joinToString("") } ?: intArrayOf(-1)
    }

    private fun dfs(n: Int, depth: Int, start: Int, apeach: IntArray, ryan: IntArray) {
        if (depth == n) {
            var apeachPoint = 0
            var ryanPoint = 0

            for (i in apeach.indices) {
                if (apeach[i] == 0 && ryan[i] == 0) continue

                if (apeach[i] >= ryan[i]) apeachPoint += 10 - i
                else ryanPoint += 10 - i
            }

            if (ryanPoint > apeachPoint) {
                val diff = ryanPoint - apeachPoint

                if (max == diff) answer.add(ryan.copyOf())
                else if (max < diff) {
                    max = diff
                    answer.apply {
                        clear()
                        add(ryan.copyOf())
                    }
                }
            }
            return
        }

        for (i in start until apeach.size) {
            ++ryan[i]
            dfs(n, depth + 1, i, apeach, ryan)
            --ryan[i]
        }
    }
}