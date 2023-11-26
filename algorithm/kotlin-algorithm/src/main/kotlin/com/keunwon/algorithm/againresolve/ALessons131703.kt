package com.keunwon.algorithm.againresolve

class ALessons131703 {
    private lateinit var board: Array<IntArray>
    private lateinit var target: Array<IntArray>
    private var answer = Int.MAX_VALUE

    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        this.board = beginning
        this.target = target
        dfs(0, 0)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(row: Int, count: Int) {
        if (row == board.size) {
            var turnCount = count
            var flag = true

            for (i in board[0].indices) {
                val num = board.indices.count { board[it][i] == target[it][i] }.let {
                    when (it) {
                        0 -> 1
                        board.size -> 0
                        else -> -1
                    }
                }
                if (num == -1) {
                    flag = false
                    break
                }
                turnCount += num
            }
            if (flag) answer = answer.coerceAtMost(turnCount)
            return
        }

        turn(row)
        dfs(row + 1, count + 1)
        turn(row)
        dfs(row + 1, count)
    }

    private fun turn(row: Int) = board[row].forEachIndexed { index, num -> board[row][index] = num xor 1 }
}

fun main() {
    ALessons131703().solution(
        arrayOf(
            intArrayOf(0, 1, 0, 0, 0),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(0, 1, 1, 1, 0),
            intArrayOf(1, 0, 1, 1, 0),
            intArrayOf(0, 1, 0, 1, 0)
        ),
        arrayOf(
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1)
        )
    ).also(::println)

    ALessons131703().solution(
        arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        ),
        arrayOf(
            intArrayOf(1, 0, 1),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
    ).also(::println)
}
