package algorithm.programmers

import java.util.Stack

class Lesson72410 {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        val stack = Stack<Int>()
        var result = 0

        for (m in moves) {
            for (i in board.indices) {
                val type = board[i][m - 1]
                if (type != 0) {
                    board[i][m - 1] = 0
                    if (stack.isNotEmpty() && stack.peek() == type) {
                        stack.pop()
                        result += 2
                    } else {
                        stack.push(type)
                    }
                    break
                }
            }
        }

        return result
    }
}
