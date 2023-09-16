package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 빙고
 * Level: 실버-4
 **/
class Problem2578 {
    private val visited = Array(5) { BooleanArray(5) }

    fun solution(board1: Array<IntArray>, board2: Array<IntArray>): Int {
        var count = 0

        for (i in board2.indices) {
            for (num in board2[i]) {
                binggo(board1, num)
                count++

                val sum = countByCol() + countByRow() + countByDiagonal()
                if (sum >= 3) return count
            }
        }
        return -1
    }

    private fun binggo(board: Array<IntArray>, target: Int) {
        for (i in board.indices) {
            for ((j, num) in board[i].withIndex()) {
                if (num == target) {
                    visited[i][j] = true
                    return
                }
            }
        }
    }

    private fun countByCol(): Int = visited.count { arr -> arr.all { it } }

    private fun countByRow(): Int {
        var count = 0
        for (i in visited[0].indices) {
            var flag = true
            for (j in visited.indices) {
                if (!visited[j][i]) {
                    flag = false
                    break
                }
            }
            if (flag) count++
        }
        return count
    }

    private fun countByDiagonal(): Int {
        var count = 0
        var tmpIndex = 0
        var flag = true

        for (i in visited.indices) {
            if (!visited[i][tmpIndex++]) {
                flag = false
                break
            }
        }
        if (flag) count++

        tmpIndex = visited[0].lastIndex
        flag = true

        for (i in visited.indices) {
            if (!visited[i][tmpIndex--]) {
                flag = false
                break
            }
        }
        if (flag) count++

        return count
    }
}

fun main() = System.`in`.bufferedReader().use { br ->
    System.out.bufferedWriter().use { bw ->
        val board1 = Array(5) {
            val st = StringTokenizer(br.readLine())
            IntArray(5) { st.nextToken().toInt() }
        }
        val board2 = Array(5) {
            val st = StringTokenizer(br.readLine())
            IntArray(5) { st.nextToken().toInt() }
        }

        Problem2578().solution(board1, board2).also { bw.write("$it") }
        bw.flush()
    }
}
