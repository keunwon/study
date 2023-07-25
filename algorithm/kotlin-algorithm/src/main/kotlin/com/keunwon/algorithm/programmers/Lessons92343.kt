package com.keunwon.algorithm.programmers

/**
 * Title: 양과 늑대
 * Level: 3
 **/
class Lessons92343 {
    private lateinit var graph: Array<MutableList<Int>>
    private lateinit var info: IntArray
    private var answer = 0

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        this.info = info
        this.graph = Array(info.size) { mutableListOf<Int>() }.apply {
            edges.forEach { (a, b) -> this[a].add(b) }
        }
        dfs(0, 0, 0, listOf(0))
        return answer
    }

    private fun dfs(index: Int, sheepCount: Int, wolfCount: Int, list: List<Int>) {
        var sCount = sheepCount
        var wCount = wolfCount

        if (info[index] == 0) sCount++ else wCount++
        if (sCount <= wCount) return

        answer = answer.coerceAtLeast(sCount)

        val next = list.toMutableList().apply {
            remove(index)
            graph[index].forEach(::add)
        }
        next.forEach { dfs(it, sCount, wCount, next) }
    }
}
