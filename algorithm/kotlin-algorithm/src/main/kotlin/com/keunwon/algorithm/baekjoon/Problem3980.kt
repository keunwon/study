package com.keunwon.algorithm.baekjoon

/**
 * Title: 선발 명단
 * Level: 골드-5
 **/
class Problem3980 {
    private lateinit var positions: Array<IntArray>
    private lateinit var visited: BooleanArray
    private var answer = 0

    fun solution(positions: Array<IntArray>): Int {
        this.positions = positions
        this.visited = BooleanArray(positions.size)

        dfs(0, 0)
        return answer
    }

    private fun dfs(depth: Int, total: Int) {
        if (depth == positions.size) {
            answer = answer.coerceAtLeast(total)
            return
        }

        for (i in positions.indices) {
            if (visited[i] || positions[depth][i] == 0) continue

            visited[i] = true
            dfs(depth + 1, total + positions[depth][i])
            visited[i] = false
        }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val c = readLine().toInt()
    repeat(c) {
        val arr = Array(11) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        Problem3980().solution(arr).also {
            bw.write("$it")
            bw.newLine()
        }
    }

    bw.flush()
    bw.close()
    close()
}
