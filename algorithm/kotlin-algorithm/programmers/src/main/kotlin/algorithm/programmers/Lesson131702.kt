package algorithm.programmers

class Lesson131702 {
    fun solution(clockHands: Array<IntArray>): Int {
        var min = 1e9.toInt()

        for (rotateInfo in product(clockHands[0].size)) {
            val board = Array(clockHands.size) { clockHands[it].copyOf() }
            var changeCount = 0

            for ((i, n) in rotateInfo.withIndex()) {
                rotate(board, 0, i, n)
                changeCount += n
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

    private fun product(size: Int): List<IntArray> {
        val moves = intArrayOf(0, 1, 2, 3)
        val list = mutableListOf<IntArray>()

        fun dfs(arr: IntArray, depth: Int) {
            if (depth == arr.size) {
                list.add(arr.copyOf())
                return
            }

            for (m in moves) {
                arr[depth] = m
                dfs(arr, depth + 1)
            }
        }

        dfs(IntArray(size), 0)
        return list
    }
}
