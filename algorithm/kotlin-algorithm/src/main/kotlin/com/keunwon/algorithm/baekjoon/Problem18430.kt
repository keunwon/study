package com.keunwon.algorithm.baekjoon

/**
 * Title: 무기 공학
 * Level: 골드-4
 **/
class Problem18430 {
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>
    private var answer = 0

    fun solution(map: Array<IntArray>): Int {
        this.map = map
        this.visited = Array(map.size) { BooleanArray(map[0].size) }

        dfs(0, 0)
        return answer
    }

    private fun dfs(index: Int, total: Int) {
        if (index == map.size * map[0].size) {
            answer = answer.coerceAtLeast(total)
            return
        }

        val r = index / map[0].size
        val c = index % map[0].size

        if (visited[r][c]) {
            dfs(index + 1, total)
            return
        }

        val moves = arrayOf(
            arrayOf(Pair(r, c - 1), Pair(r + 1, c)),
            arrayOf(Pair(r - 1, c), Pair(r, c - 1)),
            arrayOf(Pair(r - 1, c), Pair(r, c + 1)),
            arrayOf(Pair(r, c + 1), Pair(r + 1, c)),
        )

        for (move in moves) {
            val (r1, c1) = move[0]
            val (r2, c2) = move[1]

            if (!isRange(r1, c1, r2, c2)) continue
            if (visited[r1][c1] || visited[r2][c2]) continue

            visited[r1][c1] = true
            visited[r2][c2] = true
            visited[r][c] = true

            val sum = map[r1][c1] + map[r2][c2] + map[r][c] * 2
            dfs(index + 1, total + sum)

            visited[r1][c1] = false
            visited[r2][c2] = false
            visited[r][c] = false
        }
        dfs(index + 1, total)
    }

    private fun isRange(r1: Int, c1: Int, r2: Int, c2: Int): Boolean {
        return intArrayOf(r1, r2).all { it in map.indices } &&
                intArrayOf(c1, c2).all { it in map[0].indices }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val arr = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    Problem18430().solution(arr).also { bw.write("$it") }

    bw.flush()
    bw.close()
    close()
}
