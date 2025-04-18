package algorithm.baekjoon

/**
 * <p>
 * 이름: 낚시왕
 * 난이도: 골드-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17143">낚시왕 (골드-1)</a>
 **/
class Problem17143 {
    private lateinit var board: Array<Array<Shark?>>
    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)

    fun solution(r: Int, c: Int, sharkInfos: Array<IntArray>): Int {
        this.board = Array(r) { Array<Shark?>(c) { null } }.apply {
            sharkInfos.forEach { (sr, sc, s, d, z) ->
                val dir = when (d) {
                    1 -> 0
                    3 -> 1
                    4 -> 3
                    else -> d
                }
                this[sr - 1][sc - 1] = Shark(sr - 1, sc - 1, s, dir, z)
            }
        }
        return (0 until c).sumOf {
            val fish = fishing(it)
            swim()
            fish
        }
    }

    private fun swim() {
        val tmpBoard = Array(board.size) { Array<Shark?>(board[0].size) { null } }

        for (i in board.indices) {
            for (j in board[i].indices) {
                val cur = board[i][j] ?: continue
                val shark = Shark(cur.r, cur.c, cur.speed, cur.dir, cur.size)
                val speed = when (shark.dir) {
                    0, 2 -> shark.speed % (board.lastIndex * 2)
                    1, 3 -> shark.speed % (board[0].lastIndex * 2)
                    else -> shark.speed
                }

                for (k in 0 until speed) {
                    val (mr, mc) = moves[shark.dir]
                    val nr = shark.r + mr
                    val nc = shark.c + mc

                    if (nr in board.indices && nc in board[0].indices) {
                        shark.r = nr
                        shark.c = nc
                    } else {
                        shark.r += -mr
                        shark.c += -mc
                        shark.dir = (shark.dir + 2) % moves.size
                    }
                }

                if (tmpBoard[shark.r][shark.c] == null || (tmpBoard[shark.r][shark.c]!!.size < shark.size)) {
                    tmpBoard[shark.r][shark.c] = shark
                }
            }
        }

        for (i in board.indices) {
            System.arraycopy(tmpBoard[i], 0, board[i], 0, board[i].size)
        }
    }

    private fun fishing(c: Int): Int {
        for (i in board.indices) {
            val shark = board[i][c] ?: continue
            board[i][c] = null
            return shark.size
        }
        return 0
    }

    private class Shark(
        var r: Int,
        var c: Int,
        val speed: Int,
        var dir: Int,
        val size: Int,
    )
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (r, c, m) = br.readLine().split(" ").map { it.toInt() }
    val sharkInfos = Array(m) {
        val arr = br.readLine().split(" ")
        IntArray(5) { arr[it].toInt() }
    }

    Problem17143().solution(r, c, sharkInfos).also(::println)
}
