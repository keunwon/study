package com.keunwon.algorithm.againresolve

/**
 * Title: 무기 공학
 * Level: 골드-4
 **/
class AProblem18430 {
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>

    private var n = 0
    private var m = 0
    private var answer = 0

    fun solution(n: Int, m: Int, map: Array<IntArray>): Int {
        this.n = n
        this.m = m
        this.map = map
        this.visited = Array(n) { BooleanArray(m) }

        dfs(0, 0)
        return answer
    }

    private fun dfs(count: Int, sum: Int) {
        if (n * m == count) {
            answer = answer.coerceAtLeast(sum)
            return
        }

        val r = count / m
        val c = count % m

        if (!visited[r][c]) {
            val moves = arrayOf(
                arrayOf(Pair(r, c - 1), Pair(r + 1, c)),
                arrayOf(Pair(r - 1, c), Pair(r, c - 1)),
                arrayOf(Pair(r - 1, c), Pair(r, c + 1)),
                arrayOf(Pair(r, c + 1), Pair(r + 1, c)),
            )

            for (move in moves) {
                val (nr1, nc1) = move[0]
                val (nr2, nc2) = move[1]

                if (!check(nr1, nc1, nr2, nc2)) continue
                if (visited[nr1][nc1] || visited[nr2][nc2]) continue

                visited[nr1][nc1] = true
                visited[r][c] = true
                visited[nr2][nc2] = true

                val calcNum = sum + map[nr1][nc1] + map[r][c] * 2 + map[nr2][nc2]
                dfs(count + 1, calcNum)

                visited[nr1][nc1] = false
                visited[r][c] = false
                visited[nr2][nc2] = false
            }
        }
        dfs(count + 1, sum)
    }

    private fun check(r1: Int, c1: Int, r2: Int, c2: Int): Boolean {
        return intArrayOf(r1, r2).all { it in map.indices } &&
                intArrayOf(c1, c2).all { it in map[0].indices }
    }
}

fun main() = with(System.`in`.bufferedReader()) {
    val bw = System.out.bufferedWriter()

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = Array(n) {
        readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    AProblem18430().solution(n, m, map).also { bw.write("$it") }

    bw.flush()
    bw.close()
    close()
}
