package algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 구슬 탈출 2
 * 난이도: 골드-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/13460">구슬 탈출 2 (골드-1)</a>
 **/
class Problem13460 {
    fun solution(n: Int, m: Int, board: Array<CharArray>): Int {
        val visited = Array(n) { Array(m) { Array(n) { BooleanArray(m) } } }
        var (rx, ry, bx, by) = IntArray(4)

        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (type == 'R') {
                    rx = i
                    ry = j
                } else if (type == 'B') {
                    bx = i
                    by = j
                }
            }
        }

        val q = LinkedList<Ball>().apply { offer(Ball(rx, ry, bx, by, 0)) }
        val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
        visited[rx][ry][bx][by] = true

        while (q.isNotEmpty()) {
            val cur = q.poll()

            if (cur.count >= 10) return -1

            for (dir in moves.indices) {
                val (mx, my) = moves[dir]
                var blueX = cur.bx
                var blueY = cur.by
                var isBlueHole = false

                while (board[blueX + mx][blueY + my] != '#') {
                    blueX += mx
                    blueY += my

                    if (board[blueX][blueY] == 'O') {
                        isBlueHole = true
                        break
                    }
                }
                if (isBlueHole) continue

                var redX = cur.rx
                var redY = cur.ry
                var isRedHole = false

                while (board[redX + mx][redY + my] != '#') {
                    redX += mx
                    redY += my

                    if (board[redX][redY] == 'O') {
                        isRedHole = true
                        break
                    }
                }
                if (isRedHole) return cur.count + 1

                if (blueX == redX && blueY == redY) {
                    when (dir) {
                        0 -> if (cur.rx > cur.bx) ++redX else ++blueX
                        1 -> if (cur.ry > cur.by) --blueY else --redY
                        2 -> if (cur.rx > cur.bx) --blueX else --redX
                        3 -> if (cur.ry > cur.by) ++redY else ++blueY
                    }
                }

                if (!visited[redX][redY][blueX][blueY]) {
                    visited[redX][redY][blueX][blueY] = true
                    q.offer(Ball(redX, redY, blueX, blueY, cur.count + 1))
                }
            }
        }

        return -1
    }

    private class Ball(
        val rx: Int,
        val ry: Int,
        val bx: Int,
        val by: Int,
        val count: Int,
    )
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) { readln().toCharArray() }

    println(Problem13460().solution(n, m, board))
}
