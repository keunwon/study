package com.keunwon.algorithm.baekjoon

import kotlin.system.exitProcess

/**
 * Title: 스도쿠
 * Level: 골드-4
 **/
class Problem2580 {
    private lateinit var board: Array<IntArray>

    fun solution(board: Array<IntArray>) {
        this.board = board
        dfs(0, 0)
    }

    private fun dfs(r: Int, c: Int) {
        if (c == 9) {
            dfs(r + 1, 0)
            return
        }

        if (r == 9) {
            val sb = StringBuilder()

            for (i in board.indices) {
                sb.append(board[i].joinToString(" "))
                sb.append(System.lineSeparator())
            }
            println(sb.toString())
            exitProcess(0)
        }

        if (board[r][c] != 0) {
            dfs(r, c + 1)
            return
        }

        for (i in 1..9) {
            if (check(r, c, i)) {
                board[r][c] = i
                dfs(r, c + 1)
            }
        }
    }

    private fun check(r: Int, c: Int, target: Int): Boolean {
        for (i in board.indices) {
            if (board[i][c] == target) return false
        }

        for (i in board[0].indices) {
            if (board[r][i] == target) return false
        }

        val sr = r / 3 * 3
        val sc = c / 3 * 3

        for (i in sr until sr + 3) {
            for (j in sc until sc + 3) {
                if (board[i][j] == target) return false
            }
        }
        return true
    }
}

fun main() {
    val board = Array(9) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }
    Problem2580().solution(board)
}
