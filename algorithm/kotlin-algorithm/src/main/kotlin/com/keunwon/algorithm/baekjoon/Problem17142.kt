package com.keunwon.algorithm.baekjoon

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

/**
 * <p>
 * 이름: 연구소3
 * 난이도: 골드-3
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/17142">연구소3 (골드-3)</a>
 **/
class Problem17142 {
    private lateinit var board: Array<IntArray>
    private lateinit var viruses: List<Virus>

    private val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
    private var result = 1e9.toInt()

    fun solution(m: Int, board: Array<IntArray>): Int {
        this.board = board
        this.viruses = mutableListOf<Virus>().apply {
            for (i in board.indices) {
                for ((j, type) in board[i].withIndex()) {
                    if (type == 2) add(Virus(i, j))
                }
            }
        }.toList()

        dfs(0, mutableListOf(), m)
        return if (result == 1e9.toInt()) -1 else result
    }

    private fun dfs(index: Int, picks: MutableList<Virus>, m: Int) {
        if (m == picks.size) {
            val q = LinkedList<Virus>()
            val visited = Array(board.size) { BooleanArray(board.size) }
            var second = 0

            picks.forEach {
                q.offer(it)
                visited[it.r][it.c] = true
            }

            while (q.isNotEmpty()) {
                val size = q.size
                var flag = false

                repeat(size) {
                    val cur = q.poll()

                    for ((mr, mc) in moves) {
                        val nr = cur.r + mr
                        val nc = cur.c + mc

                        if (nr !in board.indices || nc !in board[0].indices) continue
                        if (board[nr][nc] == 1 || visited[nr][nc]) continue

                        if (board[nr][nc] == 0) flag = true
                        visited[nr][nc] = true
                        q.offer(Virus(nr, nc))
                    }
                }

                if (flag) ++second
            }

            for (i in board.indices) {
                for ((j, n) in board[i].withIndex()) {
                    if (n == 0 && !visited[i][j]) return
                }
            }

            result = result.coerceAtMost(second)
            return
        }

        for (i in index until viruses.size) {
            picks.add(viruses[i])
            dfs(i + 1, picks, m)
            picks.removeAt(picks.lastIndex)
        }
    }

    private class Virus(val r: Int, val c: Int)
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val board = Array(n) {
        val arr = readLine().split(" ")
        IntArray(n) { arr[it].toInt() }
    }

    println(Problem17142().solution(m, board))
}
