package com.keunwon.algorithm.againresolve

class ALessons92344 {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val prefixSum = Array(board.size + 1) { IntArray(board[0].size + 1) }

        for (s in skill) {
            val (type, r1, c1, r2, c2) = s
            val degree = s.last() * if (type == 1) -1 else 1

            prefixSum[r1][c1] += degree
            prefixSum[r1][c2 + 1] -= degree
            prefixSum[r2 + 1][c1] -= degree
            prefixSum[r2 + 1][c2 + 1] += degree
        }

        for (i in prefixSum[0].indices) {
            for (j in 1 until prefixSum.size) {
                prefixSum[j][i] += prefixSum[j - 1][i]
            }
        }

        for (i in prefixSum.indices) {
            for (j in 1 until prefixSum[0].size) {
                prefixSum[i][j] += prefixSum[i][j - 1]
            }
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                board[i][j] += prefixSum[i][j]
            }
        }
        return board.sumOf { arr -> arr.count { it > 0 } }
    }
}

fun main() {
    ALessons92344().solution(
        arrayOf(
            intArrayOf(5, 5, 5, 5, 5),
            intArrayOf(5, 5, 5, 5, 5),
            intArrayOf(5, 5, 5, 5, 5),
            intArrayOf(5, 5, 5, 5, 5)
        ),
        arrayOf(
            intArrayOf(1, 0, 0, 3, 4, 4),
            intArrayOf(1, 2, 0, 2, 3, 2),
            intArrayOf(2, 1, 0, 3, 1, 2),
            intArrayOf(1, 0, 1, 3, 3, 1)
        )
    ).also(::println)

    ALessons92344().solution(
        arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        ),
        arrayOf(
            intArrayOf(1, 1, 1, 2, 2, 4),
            intArrayOf(1, 0, 0, 1, 1, 2),
            intArrayOf(2, 2, 0, 2, 0, 100)
        )
    ).also(::println)
}
