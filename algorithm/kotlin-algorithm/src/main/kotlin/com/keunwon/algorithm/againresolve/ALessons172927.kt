package com.keunwon.algorithm.againresolve

class ALessons172927 {
    private val fatigues = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(5, 1, 1),
        intArrayOf(25, 5, 1)
    )
    private var answer = Int.MAX_VALUE

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        backtracking(0, IntArray(picks.sum()), picks, minerals)
        return answer
    }

    private fun backtracking(
        depth: Int,
        tools: IntArray,
        picks: IntArray,
        minerals: Array<String>,
    ) {
        if (depth == tools.size) {
            answer = answer.coerceAtMost(calcFatigues(tools, minerals))
            return
        }
        for (i in picks.indices) {
            if (picks[i] == 0) continue

            --picks[i]
            tools[depth] = i
            backtracking(depth + 1, tools, picks, minerals)
            ++picks[i]
        }
    }

    private fun calcFatigues(tools: IntArray, minerals: Array<String>): Int {
        var result = 0
        var index = 0

        for (tool in tools) {
            repeat(5) {
                if (minerals.size == index) return result

                val id = when (minerals[index]) {
                    "diamond" -> 0
                    "iron" -> 1
                    "stone" -> 2
                    else -> error("not found type(${minerals[index]})")
                }
                result += fatigues[tool][id]
                ++index
            }
        }
        return result
    }
}

fun main() {
    ALessons172927().solution(
        intArrayOf(1, 3, 2),
        arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")
    ).also(::println)

    ALessons172927().solution(
        intArrayOf(0, 1, 1),
        arrayOf(
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
            "diamond"
        )
    ).also(::println)
}
