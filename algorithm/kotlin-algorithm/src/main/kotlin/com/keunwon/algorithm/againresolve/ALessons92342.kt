package com.keunwon.algorithm.againresolve

class ALessons92342 {
    private val answer = mutableListOf<IntArray>()
    private var max = 0

    fun solution(n: Int, info: IntArray): IntArray {
        dfs(0, 0, n, info, IntArray(info.size))
        return answer.maxByOrNull { it.reversed().joinToString("") } ?: intArrayOf(-1)
    }

    private fun dfs(depth: Int, start: Int, n: Int, apeach: IntArray, ryan: IntArray) {
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

        for (i in start..10) {
            ryan[i]++
            dfs(depth + 1, i, n, apeach, ryan)
            ryan[i]--
        }
    }
}

fun main() {
    ALessons92342().solution(
        5,
        intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)
    ).also { println(it.contentToString()) }

    ALessons92342().solution(
        1,
        intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    ).also { println(it.contentToString()) }

    ALessons92342().solution(
        9,
        intArrayOf(0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1)
    ).also { println(it.contentToString()) }

    ALessons92342().solution(
        10,
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3)
    ).also { println(it.contentToString()) }
}
