package com.keunwon.algorithm.leetcode

import kotlin.math.abs

class `51_N_Queens` {
    private val result = mutableListOf<List<String>>()

    fun solveNQueens(n: Int): List<List<String>> {
        dfs(0, IntArray(n), BooleanArray(n))
        return result
    }

    private fun dfs(depth: Int, picks: IntArray, visited: BooleanArray) {
        if (depth == picks.size) {
            val list = picks.map {
                val sb = StringBuilder(".".repeat(depth - 1))
                sb.insert(it, 'Q')
                sb.toString()
            }
            result.add(list)
            return
        }

        for (i in visited.indices) {
            if (!visited[i]) {
                visited[i] = true
                picks[depth] = i

                if (check(depth, picks, i)) dfs(depth + 1, picks, visited)
                visited[i] = false
            }
        }
    }

    private fun check(depth: Int, picks: IntArray, cur: Int): Boolean {
        for (i in 0 until depth) {
            if (depth - i == abs(picks[i] - cur)) return false
        }
        return true
    }
}

fun main() {
    `51_N_Queens`().solveNQueens(4)
        .forEach { println(it.joinToString(" ")) }

    `51_N_Queens`().solveNQueens(1)
        .forEach { println(it.joinToString("\n")) }
}
