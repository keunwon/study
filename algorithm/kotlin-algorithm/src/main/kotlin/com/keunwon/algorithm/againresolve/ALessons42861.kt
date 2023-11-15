package com.keunwon.algorithm.againresolve

class ALessons42861 {
    private lateinit var parent: IntArray

    fun solution(n: Int, costs: Array<IntArray>): Int {
        this.parent = IntArray(n) { it }
        costs.sortBy { it[2] }
        return costs.fold(0) { acc, (s, e, d) ->
            if (find(s) != find(e)) {
                union(s, e)
                acc + d
            } else acc
        }
    }

    private fun find(n: Int): Int {
        return if (parent[n] == n) n
        else {
            parent[n] = find(parent[n])
            parent[n]
        }
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }
}

fun main() {
    ALessons42861().solution(
        4,
        arrayOf(
            intArrayOf(0, 1, 1),
            intArrayOf(0, 2, 2),
            intArrayOf(1, 2, 5),
            intArrayOf(1, 3, 1),
            intArrayOf(2, 3, 8)
        )
    ).also(::println)
}
