package algorithm.programmers

import java.util.*

class Lesson64061 {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        val stack = Stack<Int>()
        var result = 0

        for (m in moves) {
            for (i in board.indices) {
                val doll = board[i][m - 1]
                board[i][m - 1] = 0

                if (doll != 0) {
                    if (stack.isNotEmpty() && stack.peek() == doll) {
                        stack.pop()
                        result += 2
                    } else {
                        stack.push(doll)
                    }
                    break
                }
            }
        }
        return result
    }
}
