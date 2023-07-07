package com.keunwon.algorithm.programmers

/**
 * Title: 크레인 인형뽑기 게임
 * Level: 1
 **/
class Lessons64061 {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        val basket = mutableListOf<Int>()
        for (move in moves) {
            for (i in board.indices) {
                if (board[i][move - 1] != 0) {
                    basket.add(board[i][move - 1])
                    board[i][move - 1] = 0
                    break
                }
            }
        }

        var answer = 0
        while (true) {
            var remove = false

            for (i in 0 until basket.lastIndex) {
                if (basket[i] == basket[i + 1]) {
                    answer += 2
                    remove = true
                    repeat(2) { basket.removeAt(i) }
                    break
                }
            }
            if (!remove) break
        }
        return answer
    }
}
