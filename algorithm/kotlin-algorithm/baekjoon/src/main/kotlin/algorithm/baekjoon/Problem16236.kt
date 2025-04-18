package algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 아기 상어
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/16236">아기 상어 (골드-3)</a>
 **/
class Problem16236 {
    fun solution(board: Array<IntArray>): Int {
        var shark: Fish = run {
            for (i in board.indices) {
                for ((j, n) in board[i].withIndex()) {
                    if (n == 9) {
                        board[i][j] = 0
                        return@run Fish(i, j, 0)
                    }
                }
            }
            return -1
        }
        var sharkSize = 2
        var eatCount = 0
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

        while (true) {
            val q = PriorityQueue<Fish>().apply { offer(shark) }
            val visited = Array(board.size) { BooleanArray(board[0].size) }.apply { this[shark.r][shark.c] = true }
            var isEat = false

            while (q.isNotEmpty()) {
                val cur = q.poll()

                if (board[cur.r][cur.c] in 1 until sharkSize) {
                    board[cur.r][cur.c] = 0
                    ++eatCount
                    isEat = true

                    if (eatCount == sharkSize) {
                        ++sharkSize
                        eatCount = 0
                    }
                    shark = cur
                    break
                }

                for ((mr, mc) in moves) {
                    val nr = cur.r + mr
                    val nc = cur.c + mc

                    if (nr !in board.indices || nc !in board[0].indices) continue
                    if (visited[nr][nc] || board[nr][nc] > sharkSize) continue

                    visited[nr][nc] = true
                    q.offer(Fish(nr, nc, cur.second + 1))
                }
            }

            if (!isEat) break
        }

        return shark.second
    }

    private class Fish(val r: Int, val c: Int, val second: Int) : Comparable<Fish> {
        override fun compareTo(other: Fish): Int {
            return if (second == other.second) {
                if (r == other.r) c.compareTo(other.c)
                else r.compareTo(other.r)
            } else {
                second.compareTo(other.second)
            }
        }
    }
}

fun main() {
    val n = readln().toInt()
    val board = Array(n) {
        val arr = readln().split(" ")
        IntArray(n) { arr[it].toInt() }
    }

    println(Problem16236().solution(board))
}
