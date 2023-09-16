package com.keunwon.algorithm.baekjoon

/**
 * Title: 달팽이
 * Level: 실버-3
 **/
class Problem1913 {
    private val moves = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)

    fun solution(n: Int, m: Int): Answer {
        val board = createSnailBoard(n)
        val p = getPosition(m, board)
        return Answer(board, p)
    }

    private fun createSnailBoard(n: Int): Array<IntArray> {
        val board = Array(n) { IntArray(n) }
        var num = n * n
        var (r, c, dir) = IntArray(3)

        while (num > 0) {
            board[r][c] = num--

            val nr = r + moves[dir].first
            val nc = c + moves[dir].second
            val nDir = nextDir(nr, nc, dir, board)

            if (dir == nDir) {
                r = nr
                c = nc
            } else {
                r += moves[nDir].first
                c += moves[nDir].second
                dir = nDir
            }
        }
        return board
    }

    private fun nextDir(r: Int, c: Int, dir: Int, board: Array<IntArray>): Int {
        return when {
            r !in board.indices || c !in board[0].indices -> (dir + 1) % 4
            board[r][c] != 0 -> (dir + 1) % 4
            else -> dir
        }
    }

    private fun getPosition(m: Int, board: Array<IntArray>): Pair<Int, Int> {
        for (i in board.indices) {
            for ((j, num) in board[i].withIndex()) {
                if (m == num) return Pair(i + 1, j + 1)
            }
        }
        error("$m not found...")
    }

    class Answer(val map: Array<IntArray>, val position: Pair<Int, Int>)
}

fun main() {
    val n = readLine()!!.toInt()
    val m = readLine()!!.toInt()
    val answer = Problem1913().solution(n, m)

    with(answer) {
        map.forEach { println(it.joinToString(" ")) }
        println("${position.first} ${position.second}")
    }
}
