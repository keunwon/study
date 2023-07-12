package com.keunwon.algorithm.programmers

/**
 * Title: 광물 캐기
 * Level: 2
 **/
class Lessons172927 {
    private val fatigues = arrayOf(
        arrayOf(1, 1, 1),
        arrayOf(5, 1, 1),
        arrayOf(25, 5, 1),
    )
    private var answer = Int.MAX_VALUE

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        dfs(0, IntArray(picks.sum()), picks, minerals)
        return answer
    }

    private fun dfs(depth: Int, tools: IntArray, picks: IntArray, minerals: Array<String>) {
        if (depth == tools.size) {
            answer = answer.coerceAtMost(sumByFatigue(tools, minerals))
            return
        }

        for (i in picks.indices) {
            if (picks[i] == 0) continue

            picks[i]--
            tools[depth] = i
            dfs(depth + 1, tools, picks, minerals)
            picks[i]++
        }
    }

    private fun sumByFatigue(tools: IntArray, minerals: Array<String>): Int {
        var result = 0
        var mineralIndex = 0

        for (tool in tools) {
            repeat(5) {
                if (mineralIndex == minerals.size) return result

                val mineralId = getMineralId(minerals[mineralIndex])
                result += fatigues[tool][mineralId]
                mineralIndex++
            }
        }
        return result
    }

    private fun getMineralId(mineral: String): Int = when (mineral) {
        "diamond" -> 0
        "iron" -> 1
        "stone" -> 2
        else -> error("")
    }
}
