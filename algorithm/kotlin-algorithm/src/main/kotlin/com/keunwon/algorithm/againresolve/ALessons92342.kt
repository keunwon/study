package com.keunwon.algorithm.againresolve

class ALessons92342 {
    private val answer = mutableListOf<IntArray>()
    private var max = Int.MIN_VALUE

    fun solution(n: Int, info: IntArray): IntArray {
        dfs(0, 0, n, info, IntArray(11))
        return answer.maxByOrNull { it.joinToString("").reversed() } ?: intArrayOf(-1)
    }

    private fun dfs(depth: Int, startIndex: Int, n: Int, apeach: IntArray, ryan: IntArray) {
        if (depth == n) {
            var apeachPoint = 0
            var ryanPoint = 0

            for (i in 0..10) {
                if (apeach[i] == 0 && ryan[i] == 0) continue

                if (apeach[i] >= ryan[i]) apeachPoint += 10 - i
                else ryanPoint += 10 - i
            }

            if (apeachPoint < ryanPoint) {
                val diff = ryanPoint - apeachPoint
                if (max == diff) answer.add(ryan.copyOf())
                else if (max < diff) {
                    answer.clear()
                    answer.add(ryan.copyOf())
                    max = diff
                }
            }
            return
        }
        for (i in startIndex..10) {
            ryan[i]++
            dfs(depth + 1, i, n, apeach, ryan)
            ryan[i]--
        }
    }
}

fun main() {
    val n = 5
    val info = intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)

    ALessons92342().solution(n, info).also { println(it.contentToString()) }
}
