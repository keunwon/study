package com.keunwon.algorithm.programmers

import kotlin.math.min

class Lesson172927 {
    private val costs = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(5, 1, 1),
        intArrayOf(25, 5, 1),
    )
    private var answer = Int.MAX_VALUE

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        dfs(0, IntArray(picks.sum()), picks, minerals)
        return answer
    }

    private fun dfs(depth: Int, tools: IntArray, picks: IntArray, minerals: Array<String>) {
        if (depth == tools.size) {
            val f = calculate(tools, minerals)
            answer = min(answer, f)
            return
        }

        for (i in picks.indices) {
            if (picks[i] != 0) {
                --picks[i]
                tools[depth] = i
                dfs(depth + 1, tools, picks, minerals)
                ++picks[i]
            }
        }
    }

    private fun calculate(tools: IntArray, minerals: Array<String>): Int {
        var result = 0
        var mIndex = 0

        for (tool in tools) {
            repeat(5) {
                val mineral = minerals[mIndex++]
                result += costs[tool][getMineralId(mineral)]

                if (mIndex == minerals.size) return result
            }
        }
        return result
    }

    private fun getMineralId(name: String): Int = when (name) {
        "diamond" -> 0
        "iron" -> 1
        "stone" -> 2
        else -> error("not support stone: $name")
    }
}