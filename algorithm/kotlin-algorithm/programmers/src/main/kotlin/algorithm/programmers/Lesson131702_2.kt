package algorithm.programmers

class Lesson131702_2 {
    fun solution(clockHands: Array<IntArray>): Int {
        var min = 1e9.toInt()

        for (i in 0 until (1 shl (2 * clockHands[0].size))) {
            val board = Array(clockHands.size) { clockHands[it].copyOf() }
            var changeCount = 0

            var ret = i
            for (i in board[0].indices) {
                val mod = ret % 4
                ret /= 4
                changeCount += mod
                rotate(board, 0, i, mod)
            }

            for (i in 1 until board.size) {
                for (j in board[i].indices) {
                    val diff = board[i - 1][j]
                    if (diff != 0) {
                        rotate(board, i, j, 4 - diff)
                        changeCount += 4 - diff
                    }
                }
            }

            if (board.all { arr -> arr.all { it == 0 } }) {
                min = min.coerceAtMost(changeCount)
            }
        }

        return min
    }

    private fun rotate(board: Array<IntArray>, r: Int, c: Int, n: Int) {
        val position = arrayOf(0 to 0, -1 to 0, 0 to 1, 1 to 0, 0 to -1)
        for ((pr, pc) in position) {
            val nr = r + pr
            val nc = c + pc

            if (nr in board.indices && nc in board[0].indices) {
                board[nr][nc] = (board[nr][nc] + n) % 4
            }
        }
    }
}

fun main() {
    val actual = Lesson131702_2().solution(
        arrayOf(
            intArrayOf(0, 3, 3, 0),
            intArrayOf(3, 2, 2, 3),
            intArrayOf(0, 3, 2, 0),
            intArrayOf(0, 3, 3, 3),
        )
    )
    println(actual)
}
