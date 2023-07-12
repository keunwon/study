package com.keunwon.algorithm.programmers

/**
 * Title: 순위
 * Level: 3
 **/
class Lessons49191 {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val league = Array(n + 1) { IntArray(n + 1) }.apply {
            results.forEach { (a, b) ->
                this[a][b] = 1
                this[b][a] = -1
            }
        }

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (league[i][k] == 1 && league[k][j] == 1) {
                        league[i][j] = 1
                        league[j][i] = -1
                    }
                    if (league[i][k] == -1 && league[k][j] == -1) {
                        league[i][j] = -1
                        league[j][i] = 1
                    }
                }
            }
        }
        return league.count { arr -> arr.count { it == 0 } == 2 }
    }
}
