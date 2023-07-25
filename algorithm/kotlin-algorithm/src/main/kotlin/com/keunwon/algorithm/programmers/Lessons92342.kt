package com.keunwon.algorithm.programmers

/**
 * Title: 양궁대회
 * Level: 2
 **/
class Lessons92342 {
    private val answer = mutableListOf<IntArray>()
    private var max = 0

    fun solution(n: Int, info: IntArray): IntArray {
        dfs(0, 0, n, IntArray(11), info)
        return answer.maxByOrNull { it.reversed().joinToString("") } ?: intArrayOf(-1)
    }

    private fun dfs(depth: Int, start: Int, n: Int, ryan: IntArray, apeach: IntArray) {
        if (depth == n) {
            var ryanPoint = 0
            var apeachPoint = 0

            for (i in 0..10) {
                if (ryan[i] == 0 && apeach[i] == 0) continue
                if (ryan[i] <= apeach[i]) apeachPoint += 10 - i
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
        for (i in start..10) {
            ryan[i]++
            dfs(depth + 1, i, n, ryan, apeach)
            ryan[i]--
        }
    }
}
