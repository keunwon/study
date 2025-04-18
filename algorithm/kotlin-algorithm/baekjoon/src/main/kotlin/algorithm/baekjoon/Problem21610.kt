package algorithm.baekjoon

/**
 * <p>
 * 이름:마법사 상어와 비바라기
 * 난이도:골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/21610">마법사 상어와 비바라기 (골드-4)</a>
 **/
class Problem21610 {
    private lateinit var board: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>

    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    private val moves = arrayOf(0 to -1, -1 to -1, -1 to 0, -1 to 1, 0 to 1, 1 to 1, 1 to 0, 1 to -1)
    private val clouds = mutableListOf<Pair<Int, Int>>()

    fun solution(board: Array<IntArray>, infos: Array<IntArray>): Int {
        this.board = board

        with(clouds) {
            add(Pair(board.lastIndex, 0))
            add(Pair(board.lastIndex, 1))
            add(Pair(board.lastIndex - 1, 0))
            add(Pair(board.lastIndex - 1, 1))
        }

        infos.forEach { (d, s) ->
            this.visited = Array(board.size) { BooleanArray(board[0].size) }
            moveClouds(d - 1, s)
            cleanCloudsAndWaterMagic()
            formClouds()
        }

        return board.sumOf { arr -> arr.sumOf { it } }
    }

    private fun formClouds() {
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] > 1 && !visited[i][j]) {
                    board[i][j] -= 2
                    clouds.add(i to j)
                }
            }
        }
    }

    private fun cleanCloudsAndWaterMagic() {
        while (clouds.isNotEmpty()) {
            val (r, c) = clouds.removeFirst()
            var count = 0
            visited[r][c] = true

            for (dir in 1 until moves.size step 2) {
                val (mr, mc) = moves[dir]
                val nr = r + mr
                val nc = c + mc

                if (nr in board.indices && nc in board[0].indices && board[nr][nc] > 0) {
                    ++count
                }
            }
            board[r][c] += count
        }
    }

    private fun moveClouds(d: Int, s: Int) {
        val (mr, mc) = moves[d]
        clouds.forEachIndexed { idx, (r, c) ->
            val nr = (r + (mr * (s % board.size)) + board.size) % board.size
            val nc = (c + (mc * (s % board[0].size)) + board[0].size) % board[0].size
            ++board[nr][nc]
            clouds[idx] = nr to nc
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = br.readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }
    val infos = Array(m) {
        val arr = br.readLine().split(" ")
        IntArray(2) { arr[it].toInt() }
    }

    Problem21610().solution(board, infos).also(::println)
}
