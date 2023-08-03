package com.keunwon.algorithm.againresolve

class ALessons42861 {
    private lateinit var parents: IntArray

    fun solution(n: Int, costs: Array<IntArray>): Int {
        this.parents = IntArray(n) { it }
        costs.sortWith(compareBy({ it[2] }, { it[1] }))

        var answer = 0
        for ((a, b, d) in costs) {
            val find1 = find(a)
            val find2 = find(b)

            if (find1 == find2) continue
            union(a, b)
            answer += d
        }
        return answer
    }

    private fun find(n: Int): Int {
        return if (parents[n] == n) n else find(parents[n])
    }

    private fun union(a: Int, b: Int) {
        val find1 = find(a)
        val find2 = find(b)
        return if (find1 < find2) parents[find2] = find1
        else parents[find1] = find2
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
