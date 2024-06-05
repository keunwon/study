package com.keunwon.algorithm.programmers

import kotlin.math.min

class Lesson131703 {
    private lateinit var beginning: Array<IntArray>
    private lateinit var target: Array<IntArray>

    private var answer = Int.MAX_VALUE

    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        this.beginning = beginning
        this.target = target
        dfs(0, 0)
        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    private fun dfs(count: Int, row: Int) {
        if (row == beginning.size) {
            var total = count
            var flag = true

            for (i in beginning[0].indices) {
                val count = beginning.indices.count { j -> beginning[j][i] == target[j][i] }
                val n = when (count) {
                    beginning.size -> 0
                    0 -> 1
                    else -> -1
                }

                if (n == -1) {
                    flag = false
                    break
                }
                total += n
            }
            if (flag) answer = min(answer, total)
            return
        }

        turn(row)
        dfs(count + 1, row + 1)
        turn(row)
        dfs(count, row + 1)
    }

    private fun turn(row: Int) {
        for ((i, num) in beginning[row].withIndex()) {
            beginning[row][i] = 1 xor num
        }
    }
}
