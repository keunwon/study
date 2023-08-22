package com.keunwon.algorithm.baekjoon

/**
 * Title: 봄버맨
 * Level: 실버-1
 **/
class Problem16918 {
    private lateinit var map: Array<CharArray>
    private lateinit var board: Array<IntArray>

    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(n: Int, map: Array<CharArray>): Array<CharArray> {
        this.map = map
        this.board = createBoard(map)
        var second = 0

        while (second++ < n) {
            if (second % 2 == 0) plant(second)
            else if (second % 2 == 1) boom(second)
        }
        return map
    }

    private fun boom(second: Int) {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == second) {
                    map[i][j] = '.'

                    for ((mr, mc) in moves) {
                        val nr = i + mr
                        val nc = j + mc

                        if (nr !in board.indices || nc !in board[0].indices) continue
                        if (map[nr][nc] == 'O' && board[nr][nc] != second) {
                            map[nr][nc] = '.'
                            board[nr][nc] = 0
                        }
                    }
                }
            }
        }
    }

    private fun plant(second: Int) {
        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] == '.') {
                    map[i][j] = 'O'
                    board[i][j] = second + 3
                }
            }
        }
    }

    private fun createBoard(map: Array<CharArray>): Array<IntArray> {
        val board = Array(map.size) { IntArray(map[0].size) }

        for (i in map.indices) {
            for (j in map[0].indices) {
                if (map[i][j] == 'O') board[i][j] = 3
            }
        }
        return board
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val (r, c, n) = br.readLine().split(" ").map { it.toInt() }
        val map = Array(r) { br.readLine().toCharArray() }

        Problem16918().solution(n, map).forEach {
            bw.write("${it.joinToString("")}")
            bw.newLine()
        }
        bw.flush()
    }
}
