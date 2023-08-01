package com.keunwon.algorithm.againresolve

class ALessons49191 {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val graph = Array(n + 1) { IntArray(n + 1) { -1 } }.apply {
            results.forEach { (a, b) ->
                this[a][b] = 1
                this[b][a] = 0
            }
        }

        for (i in 1..n) {
            for (j in 1..n) {
                for (k in 1..n) {
                    if (graph[j][i] == 1 && graph[i][k] == 1) {
                        graph[j][k] = 1
                        graph[k][j] = 0
                    }
                    if (graph[j][i] == 0 && graph[i][k] == 0) {
                        graph[j][k] = 0
                        graph[k][j] = 1
                    }
                }
            }
        }
        return graph.count { arr -> arr.count { it in intArrayOf(0, 1) } == n - 1 }
    }
}

fun main() {
    val n = 5
    val results = arrayOf(
        intArrayOf(4, 3),
        intArrayOf(4, 2),
        intArrayOf(3, 2),
        intArrayOf(1, 2),
        intArrayOf(2, 5),
    )

    ALessons49191().solution(n, results).also { println(it) }
}
