package com.keunwon.algorithm.againresolve

class ALessons42861 {
    private lateinit var parent: IntArray

    fun solution(n: Int, costs: Array<IntArray>): Int {
        this.parent = IntArray(n) { it }
        costs.sortBy { it[2] }
        return costs.fold(0) { acc, (a, b, c) ->
            if (find(a) != find(b)) {
                union(a, b)
                acc + c
            } else acc
        }
    }

    private fun find(n: Int): Int {
        return if (parent[n] == n) n else find(parent[n])
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        return if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }
}

fun main() {
    val n = 4
    val costs = arrayOf(
        intArrayOf(0, 1, 1),
        intArrayOf(0, 2, 2),
        intArrayOf(1, 2, 5),
        intArrayOf(1, 3, 1),
        intArrayOf(2, 3, 8),
    )
    ALessons42861().solution(n, costs).also { println(it) }
}
