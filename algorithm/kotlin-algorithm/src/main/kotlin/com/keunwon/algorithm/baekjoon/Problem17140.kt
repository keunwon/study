package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 이차원 배열과 연산
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17140">이차원 배열과 연산 (골드-4)</a>
 **/
class Problem17140 {
    private lateinit var board: Array<IntArray>
    private var rowSize = 0
    private var colSize = 0

    fun solution(r: Int, c: Int, k: Int, board: Array<IntArray>): Int {
        this.board = Array(101) { IntArray(101) }.apply {
            for (i in board.indices) {
                for ((j, n) in board[i].withIndex()) {
                    this[i][j] = n
                }
            }
        }
        this.rowSize = board.size
        this.colSize = board[0].size

        for (time in 0..100) {
            if (this.board[r - 1][c - 1] == k) return time

            if (rowSize >= colSize) {
                r()
            } else {
                c()
            }
        }
        return -1
    }

    private fun r() {
        val tmpBoard = Array(101) { IntArray(101) }
        var maxSize = 0

        for ((i, arr) in board.withIndex()) {
            val map = mutableMapOf<Int, Int>().apply {
                arr.forEach { if (it != 0) put(it, getOrDefault(it, 0) + 1) }
            }
            val entries = map.entries.sortedWith(compareBy({ it.value }, { it.key }))

            for ((j, entry) in entries.withIndex()) {
                val cur = j * 2
                val next = j * 2 + 1

                if (next >= tmpBoard[0].size) break

                tmpBoard[i][cur] = entry.key
                tmpBoard[i][next] = entry.value
                maxSize = maxSize.coerceAtLeast(next)
            }
        }

        colSize = maxSize
        board = tmpBoard
    }

    private fun c() {
        val tmpBoard = Array(101) { IntArray(101) }
        var maxSize = 0

        for (i in board[0].indices) {
            val map = mutableMapOf<Int, Int>().apply {
                for (j in board.indices) {
                    val n = board[j][i]
                    if (n != 0) put(n, getOrDefault(n, 0) + 1)
                }
            }
            val entries = map.entries.sortedWith(compareBy({ it.value }, { it.key }))

            for ((j, entry) in entries.withIndex()) {
                val cur = j * 2
                val next = j * 2 + 1

                if (next >= tmpBoard.size) break

                tmpBoard[cur][i] = entry.key
                tmpBoard[next][i] = entry.value
                maxSize = maxSize.coerceAtLeast(next)
            }
        }

        rowSize = maxSize
        board = tmpBoard
    }
}

fun main() {
    val (r, c, k) = readln().split(" ").map { it.toInt() }
    val arr = Array(3) {
        val arr = readln().split(" ")
        IntArray(3) { arr[it].toInt() }
    }

    Problem17140().solution(r, c, k, arr).also(::println)
}
