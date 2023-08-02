package com.keunwon.algorithm.againresolve

class ALessons92343 {
    private lateinit var info: IntArray
    private lateinit var edges: Array<IntArray>
    private lateinit var graph: Array<MutableList<Int>>

    private var answer = 0

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        this.info = info
        this.edges = edges
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
            addAll(graph[index])
        }
        next.forEach { dfs(it, sCount, wCount, next) }
    }
}

fun main() {
    val info = intArrayOf(0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1)
    val edges = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(1, 4),
        intArrayOf(0, 8),
        intArrayOf(8, 7),
        intArrayOf(9, 10),
        intArrayOf(9, 11),
        intArrayOf(4, 3),
        intArrayOf(6, 5),
        intArrayOf(4, 6),
        intArrayOf(8, 9),
    )
    ALessons92343().solution(info, edges).also { println(it) }
}
