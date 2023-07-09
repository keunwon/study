package com.keunwon.algorithm.programmers

/**
 * Title: 섬 연결하기
 * Level: 3
 **/
class Lessons42861 {
    fun solution(n: Int, costs: Array<IntArray>): Int {
        costs.sortBy { it[2] }
        val parent = IntArray(n) { it }.toMutableList()
        return costs.fold(0) { acc, (a, b, distance) ->
            if (find(parent, a) != find(parent, b)) {
                union(parent, a, b)
                acc + distance
            } else acc
        }
    }

    private fun find(parent: List<Int>, num: Int): Int {
        return if (parent[num] == num) num else find(parent, parent[num])
    }

    private fun union(parent: MutableList<Int>, num1: Int, num2: Int) {
        val find1 = find(parent, num1)
        val find2 = find(parent, num2)
        return if (find1 < find2) parent[find2] = find1
        else parent[find1] = find2
    }
}
