package algorithm.baekjoon

/**
 * <p>
 * 이름: 감시
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/15683">감시 (골드-3)</a>
 **/
class Problem15683 {
    private lateinit var board: Array<IntArray>

    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private val cctyDirs = arrayOf(
        intArrayOf(),
        intArrayOf(0),
        intArrayOf(0, 2),
        intArrayOf(0, 1),
        intArrayOf(0, 1, 2),
        intArrayOf(0, 1, 2, 3),
    )
    private val ccty = mutableListOf<Node>()
    private var result = 0

    fun solution(board: Array<IntArray>): Int {
        this.board = board

        for (i in board.indices) {
            for ((j, type) in board[i].withIndex()) {
                if (type == 0) ++result
                else if (type in 1..5) ccty.add(Node(i, j, type))
            }
        }

        combination(0, IntArray(ccty.size))
        return result
    }

    private fun combination(depth: Int, picks: IntArray) {
        if (depth == picks.size) {
            val tmpBoard = Array(board.size) { board[it].copyOf() }

            for ((i, node) in ccty.withIndex()) {
                for (dir in cctyDirs[node.type]) {
                    val mIndex = (dir + picks[i]) % moves.size
                    val (mr, mc) = moves[mIndex]
                    var nr = node.r
                    var nc = node.c

                    while (
                        nr + mr in board.indices &&
                        nc + mc in board[0].indices &&
                        tmpBoard[nr + mr][nc + mc] < 6
                    ) {
                        nr += mr
                        nc += mc

                        if (tmpBoard[nr][nc] == 0) {
                            tmpBoard[nr][nc] = -1
                        }
                    }
                }
            }

            val sum = tmpBoard.sumOf { arr -> arr.count { it == 0 } }
            result = result.coerceAtMost(sum)
            return
        }

        for (i in 0 until 4) {
            picks[depth] = i
            combination(depth + 1, picks)
        }
    }

    private class Node(val r: Int, val c: Int, val type: Int)
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = readln().split(" ")
        IntArray(m) { arr[it].toInt() }
    }

    Problem15683().solution(board).also(::println)
}
