package com.keunwon.algorithm.againresolve

class ALessons154540 {
    private lateinit var maps: Array<String>
    private lateinit var visited: Array<BooleanArray>

    fun solution(maps: Array<String>): IntArray {
        this.maps = maps
        this.visited = Array(maps.size) { BooleanArray(maps[0].length) }
        val answer = mutableListOf<Int>()

        for (i in maps.indices) {
            for (j in maps[0].indices) {
                if (!visited[i][j] && maps[i][j] != 'X') {
                    val sum = dfs(i, j)
                    answer.add(sum)
                }
            }
        }
        return if (answer.isEmpty()) intArrayOf(-1) else answer.sorted().toIntArray()
    }

    private fun dfs(r: Int, c: Int): Int {
        if (r !in maps.indices || c !in maps[0].indices) return 0
        if (visited[r][c] || maps[r][c] == 'X') return 0

        visited[r][c] = true
        return maps[r][c].digitToInt() +
                dfs(r - 1, c) +
                dfs(r, c + 1) +
                dfs(r + 1, c) +
                dfs(r, c - 1)
    }
}

fun main() {
    arrayOf(
        arrayOf("X591X", "X1X5X", "X231X", "1XXX1"),
        arrayOf("XXX", "XXX", "XXX"),
    ).forEach { maps ->
        ALessons154540().solution(maps).also { println(it.joinToString(", ")) }
    }
}
