package com.keunwon.algorithm.againresolve

class ALessons172927 {
    private val fatigues = arrayOf(intArrayOf(1, 1, 1), intArrayOf(5, 1, 1), intArrayOf(25, 5, 1))
    private val mineralIds = mapOf("diamond" to 0, "iron" to 1, "stone" to 2)
    private var answer = Int.MAX_VALUE

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        dfs(0, IntArray(picks.sum()), picks, minerals)
        return answer
    }

    private fun dfs(depth: Int, tools: IntArray, picks: IntArray, minerals: Array<String>) {
        if (depth == tools.size) {
            answer = answer.coerceAtMost(sum(tools, minerals))
            return
        }
        for ((id, count) in picks.withIndex()) {
            if (count == 0) continue

            tools[depth] = id
            picks[id]--
            dfs(depth + 1, tools, picks, minerals)
            picks[id]++
        }
    }

    private fun sum(tools: IntArray, minerals: Array<String>): Int {
        var result = 0
        var index = 0

        for (tool in tools) {
            repeat(5) {
                val mineralId = mineralIds.getValue(minerals[index++])
                result += fatigues[tool][mineralId]
                if (index == minerals.size) return result
            }
        }
        return result
    }
}

fun main() {
    val result1 = run {
        val picks = intArrayOf(1, 3, 2)
        val minerals = arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")
        ALessons172927().solution(picks, minerals)
    }
    println(result1)

    val result2 = run {
        val picks = intArrayOf(0, 1, 1)
        val minerals = arrayOf(
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "diamond",
            "iron",
            "iron",
            "iron",
            "iron",
            "iron",
            "diamond",
        )
        ALessons172927().solution(picks, minerals)
    }
    println(result2)
}
