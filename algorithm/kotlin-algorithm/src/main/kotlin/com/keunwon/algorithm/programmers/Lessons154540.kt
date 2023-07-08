package com.keunwon.algorithm.programmers

/**
 * Title: 무인도 여행
 * Level: 2
 **/
class Lessons154540 {
    private val answer = mutableListOf<Int>()

    fun solution(maps: Array<String>): IntArray {
        val visited = Array(maps.size) { BooleanArray(maps[0].length) }

        for (i in maps.indices) {
            for (j in maps[0].indices) {
                if (!visited[i][j]) {
                    val sum = dfs(i, j, visited, maps)
                    if (sum > 0) answer.add(sum)
                }
            }
        }
        return if (answer.isEmpty()) intArrayOf(-1) else answer.sorted().toIntArray()
    }

    private fun dfs(y: Int, x: Int, visited: Array<BooleanArray>, maps: Array<String>): Int {
        if (y !in maps.indices || x !in maps[0].indices) return 0
        if (visited[y][x] || maps[y][x] == 'X') return 0

        visited[y][x] = true
        return maps[y][x].digitToInt() +
                dfs(y - 1, x, visited, maps) +
                dfs(y, x + 1, visited, maps) +
                dfs(y + 1, x, visited, maps) +
                dfs(y, x - 1, visited, maps)
    }
}
