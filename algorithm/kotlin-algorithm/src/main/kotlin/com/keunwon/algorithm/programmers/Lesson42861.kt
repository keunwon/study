package com.keunwon.algorithm.programmers

class Lesson42861 {
    private lateinit var parents: IntArray

    fun solution(n: Int, costs: Array<IntArray>): Int {
        this.parents = IntArray(n) { it }
        costs.sortBy { it[2] }

        return costs.fold(0) { acc, (a, b, d) ->
            if (find(a) == find(b)) acc
            else {
                union(a, b)
                acc + d
            }
        }
    }

    private fun find(n: Int): Int = if (parents[n] == n) n else find(parents[n])

    private fun union(a: Int, b: Int) {
        val first = find(a)
        val second = find(b)

        if (first < second) parents[second] = first
        else parents[first] = second
    }
}
