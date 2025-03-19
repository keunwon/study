package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 진우의 달 여행 (Small)
 * 난이도: 실버-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17484">진우의 달 여행 (Small) (실버-3)</a>
 **/
class Problem17484 {
    private val moves = intArrayOf(-1, 0, 1)
    private var result = 1e9.toInt()

    fun solution(board: Array<IntArray>): Int {
        for (x in board[0].indices) {
            dfs(1, -1, x, board[0][x], board)
        }
        return result
    }

    private fun dfs(
        depth: Int,
        dir: Int,
        x: Int,
        sum: Int,
        board: Array<IntArray>,
    ) {
        if (depth == board.size) {
            result = result.coerceAtMost(sum)
            return
        }

        for ((i, n) in moves.withIndex()) {
            val nx = x + n
            if (i != dir && nx in board[0].indices) {
                dfs(depth + 1, i, nx, sum + board[depth][nx], board)
            }
        }
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(m) { arr[it] }
    }

    Problem17484().solution(board).also { println(it) }
}
