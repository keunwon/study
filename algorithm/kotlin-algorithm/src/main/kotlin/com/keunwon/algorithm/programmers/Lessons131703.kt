package com.keunwon.algorithm.programmers

/**
 * Title: 2차원 동전 뒤집기
 * Level: 3
 **/
class Lessons131703 {
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
            var total = count
            var flag = true

            for (i in board[0].indices) {
                val num = board.indices.count { j -> board[j][i] == target[j][i] }.let {
                    when (it) {
                        board.size -> 0
                        0 -> 1
                        else -> -1
                    }
                }

                if (num == -1) {
                    flag = false
                    break
                }
                total += num
            }
            if (flag) answer = answer.coerceAtMost(total)
            return
        }

        turn(row)
        dfs(row + 1, count + 1)
        turn(row)
        dfs(row + 1, count)
    }

    private fun turn(row: Int) {
        for ((i, n) in board[row].withIndex()) {
            board[row][i] = 1 xor n
        }
    }
}
