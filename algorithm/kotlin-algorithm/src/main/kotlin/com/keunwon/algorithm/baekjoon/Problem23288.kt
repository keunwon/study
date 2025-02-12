package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 주사위 굴리기 2
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/23288">주사위 굴리기 2 (골드-3)</a>
 **/
class Problem23288 {
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(k: Int, board: Array<IntArray>): Int {
        val dices = intArrayOf(2, 4, 1, 3, 5, 6)
        var (r, c) = 0 to 0
        var dir = 1
        var result = 0

        repeat(k) {
            if (r + moves[dir].first !in board.indices || c + moves[dir].second !in board[0].indices) {
                dir = (dir + 2) % moves.size
            }
            r += moves[dir].first
            c += moves[dir].second

            when (dir) {
                0 -> {
                    val tmp = dices[2]
                    dices[2] = dices[4]
                    dices[4] = dices[5]
                    dices[5] = dices[0]
                    dices[0] = tmp
                }

                1 -> {
                    val tmp = dices[2]
                    dices[2] = dices[1]
                    dices[1] = dices[5]
                    dices[5] = dices[3]
                    dices[3] = tmp
                }

                2 -> {
                    val tmp = dices[2]
                    dices[2] = dices[0]
                    dices[0] = dices[5]
                    dices[5] = dices[4]
                    dices[4] = tmp
                }

                3 -> {
                    val tmp = dices[2]
                    dices[2] = dices[3]
                    dices[3] = dices[5]
                    dices[5] = dices[1]
                    dices[1] = tmp
                }
            }

            val q = LinkedList<Pair<Int, Int>>().apply { offer(r to c) }
            val visited = Array(board.size) { BooleanArray(board[0].size) }.apply { this[r][c] = true }
            var count = 1

            while (q.isNotEmpty()) {
                val (cr, cc) = q.poll()

                for ((mr, mc) in moves) {
                    val nr = cr + mr
                    val nc = cc + mc

                    if (nr in board.indices && nc in board[0].indices &&
                        !visited[nr][nc] && board[nr][nc] == board[cr][cc]
                    ) {
                        visited[nr][nc] = true
                        q.offer(nr to nc)
                        ++count
                    }
                }
            }

            result += board[r][c] * count
            dir = when {
                dices[5] > board[r][c] -> (dir + 1) % moves.size
                dices[5] < board[r][c] -> (dir - 1 + moves.size) % moves.size
                else -> dir
            }
        }

        return result
    }
}

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = readln().split(" ")
        IntArray(m) { arr[it].toInt() }
    }

    Problem23288().solution(k, board).also(::println)
}
