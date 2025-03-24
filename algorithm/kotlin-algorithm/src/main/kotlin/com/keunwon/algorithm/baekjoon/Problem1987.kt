package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 알파벳
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1987">알파벳 (골드-4)</a>
 **/
class Problem1987 {
    private lateinit var board: Array<String>

    private val alphabet = BooleanArray(26)
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private var result = 0

    fun solution(board: Array<String>): Int {
        this.board = board
        alphabet[board[0][0] - 'A'] = true
        dfs(1, 0, 0)
        return result
    }

    private fun dfs(count: Int, r: Int, c: Int) {
        result = result.coerceAtLeast(count)

        for ((mr, mc) in moves) {
            val nr = r + mr
            val nc = c + mc

            if (nr in board.indices && nc in board[0].indices) {
                val aIndex = board[nr][nc] - 'A'
                if (!alphabet[aIndex]) {
                    alphabet[aIndex] = true
                    dfs(count + 1, nr, nc)
                    alphabet[aIndex] = false
                }
            }
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (r, c) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(r) { br.readLine() }

    Problem1987().solution(board).also { println(it) }
}
